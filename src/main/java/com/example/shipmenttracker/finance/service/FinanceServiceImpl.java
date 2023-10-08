package com.example.shipmenttracker.finance.service;

import com.example.shipmenttracker.chain.storage.ChainRepository;
import com.example.shipmenttracker.exception.ChainNotFoundException;
import com.example.shipmenttracker.exception.ProductNotFoundException;
import com.example.shipmenttracker.finance.dto.NewPriceDto;
import com.example.shipmenttracker.finance.dto.PriceShortDto;
import com.example.shipmenttracker.finance.mapper.PriceMapper;
import com.example.shipmenttracker.finance.storage.PriceRepository;
import com.example.shipmenttracker.product.storage.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FinanceServiceImpl implements FinanceService {

    private final ChainRepository chainRepository;
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;

    @Override
    public List<PriceShortDto> create(List<NewPriceDto> dtoList) {
        return priceRepository.saveAll(dtoList
                        .stream()
                        .map(dto -> PriceMapper.toPrice(
                                dto,
                                chainRepository.findById(dto.getChainId()).orElseThrow(() -> new ChainNotFoundException(dto.getChainId())),
                                productRepository.findById(dto.getProductId()).orElseThrow(() -> new ProductNotFoundException(dto.getProductId()))
                        )).collect(Collectors.toList()))
                .stream()
                .map(PriceMapper::toShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PriceShortDto> update(List<PriceShortDto> dtoList) {
        return priceRepository.saveAll(dtoList.stream().map(dto -> PriceMapper.toPrice(
                        dto,
                        chainRepository.getReferenceById(dto.getChainId()),
                        productRepository.getReferenceById(dto.getProductId())
                )).collect(Collectors.toList()))
                .stream()
                .map(PriceMapper::toShortDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(List<PriceShortDto> dtoList) {
        priceRepository.deleteAllByIdInBatch(dtoList.stream().map(dto -> dto.getId()).collect(Collectors.toList()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PriceShortDto> getAll() {
        return priceRepository.findAll()
                .stream()
                .map(PriceMapper::toShortDto)
                .collect(Collectors.toList());
    }
}
