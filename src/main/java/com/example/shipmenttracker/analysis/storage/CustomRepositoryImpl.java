package com.example.shipmenttracker.analysis.storage;

import com.example.shipmenttracker.analysis.dto.Request;
import com.example.shipmenttracker.analysis.model.Actual;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomRepositoryImpl implements CustomRepository {

    private final EntityManager manager;

    @Override
    public List<Actual> findAllByRequest(Request request) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Actual> query = builder.createQuery(Actual.class);
        Root<Actual> root = query.from(Actual.class);
        Predicate criteria = builder.conjunction();
        if (request.getChains() != null && request.getChains().size() > 0) {
            criteria = builder.and(criteria, root.get("chain")).in(request.getChains());
        }
        if (request.getProducts() != null && request.getProducts().size() > 0) {
            criteria = builder.and(criteria, root.get("product").in(request.getProducts()));
        }
        query.select(root).where(criteria).groupBy(root.get("fact"), root.get("chain_id"), root.get("product_id"));
        return manager.createQuery(query).setFirstResult(request.getFrom()).setMaxResults(request.getSize()).getResultList();
    }
}
