package com.example.shipmenttracker.config;

import com.example.shipmenttracker.analysis.model.Actual;
import com.example.shipmenttracker.analysis.storage.ActualRepository;
import com.example.shipmenttracker.category.model.Category;
import com.example.shipmenttracker.category.storage.CategoryRepository;
import com.example.shipmenttracker.chain.model.Chain;
import com.example.shipmenttracker.chain.storage.ChainRepository;
import com.example.shipmenttracker.customer.model.Customer;
import com.example.shipmenttracker.customer.storage.CustomerRepository;
import com.example.shipmenttracker.finance.model.Price;
import com.example.shipmenttracker.finance.storage.PriceRepository;
import com.example.shipmenttracker.product.model.Product;
import com.example.shipmenttracker.product.storage.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Transactional
public class Service {

    private final ActualRepository actualRepository;
    private final CustomerRepository customerRepository;
    private final ChainRepository chainRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;
    private final ProductRepository productRepository;

    @EventListener(ApplicationReadyEvent.class)
    void dataUpload() {
        Map<String, Chain> chains = new HashMap<>();
        chains.put("Chain 1", chainRepository.getReferenceById(1L));
        chains.put("Chain 2", chainRepository.getReferenceById(2L));
        Map<Integer, List<String>> data = parseExcel("Test.xlsx");
        uploadCustomers(data.get(3), chains);
        uploadProducts(data.get(2));
        uploadPrices(data.get(4), chains);
        uploadActuals(data.get(1), chains);
        System.out.println("Данные загружены.");
    }

    private Map<Integer, List<String>> parseExcel(String fileName) {
        Map<Integer, List<String>> data = new HashMap<>();
        for (int i = 1; i < 5; i++) {
            List<String> list = ExcelParser.parse(fileName, i);
            data.put(i, list);
        }
        return data;
    }

    private void uploadCustomers(List<String> strings, Map<String, Chain> chains) {
        List<Customer> customers = new ArrayList<>();
        for (String s : strings) {
            String[] arr = s.split("\n");
            if (!arr[0].equals("CH3 Ship To Code")) {
                customers.add(new Customer(Long.parseLong(arr[0]), arr[1], chains.get(arr[2])));
            }
        }
        customerRepository.saveAll(customers);
    }

    private void uploadProducts(List<String> strings) {
        Map<Long, Category> categories = categoryRepository.findAll().stream().collect(Collectors.toMap(k -> k.getL3ProductCategoryCode(), v -> v));
        List<Product> products = new ArrayList<>();
        for (String s : strings) {
            String[] arr = s.split("\n");
            if (!arr[0].equals("Material_No")) {
                products.add(new Product(Long.parseLong(arr[0]), arr[1], categories.get(Long.parseLong(arr[2]))));
            }
        }
        productRepository.saveAll(products);
    }

    private void uploadPrices(List<String> strings, Map<String, Chain> chains) {
        List<Price> priceList = new ArrayList<>();
        for (String s : strings) {
            String[] arr = s.split("\n");
            if (!arr[0].equals("Chain_name") && !arr[0].equals("Общий итог")) {
                if (productRepository.findById(Long.parseLong(arr[1])).isPresent()) {
                    priceList.add(new Price(null, chains.get(arr[0]), productRepository.getReferenceById(Long.parseLong(arr[1])),
                            Double.parseDouble(arr[2])));
                }
            }
        }
        priceRepository.saveAll(priceList);
    }

    private void uploadActuals(List<String> strings, Map<String, Chain> chains) {
        List<Actual> actuals = new ArrayList<>();
        for (String s : strings) {
            String[] arr = s.split("\n");
            if (!arr[0].equals("Date")) {
                if (productRepository.findById(Long.parseLong(arr[1])).isPresent() &&
                        customerRepository.findById(Long.parseLong(arr[2])).isPresent()) {
                    LocalDate date = LocalDate.parse(arr[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    Product product = productRepository.getReferenceById(Long.parseLong(arr[1]));
                    Customer customer = customerRepository.getReferenceById(Long.parseLong(arr[2]));
                    Chain chain = chains.get(arr[3]);
                    Long volume = Long.parseLong(arr[4]);
                    Double salesValue = Double.parseDouble(arr[5]);
                    Double price = priceRepository.findPriceByChainIdAndProductId(chain.getId(), product.getId()).getRegularPricePerUnit();
                    Actual actual = new Actual(null, date, product, customer, chain, volume, salesValue,
                            price < salesValue / volume);
                    actuals.add(actual);
                }
            }
        }
        actualRepository.saveAll(actuals);
    }
}
