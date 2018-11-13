package com.example.paginationgridview.specification;

import com.example.paginationgridview.entite.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.Calendar.MONDAY;

public class TestSpecification implements Specification<Test> {

    private SearchCriteria criteria;

    public TestSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }


    @Nullable
    @Override
    public Predicate toPredicate(Root<Test> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        else if (criteria.getOperation().equalsIgnoreCase("!:")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.notLike(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }

        else if (criteria.getOperation().equalsIgnoreCase("=")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()),criteria.getValue().toString());
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        else if (criteria.getOperation().equalsIgnoreCase("!=")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.notLike(
                        root.<String>get(criteria.getKey()),criteria.getValue().toString());
            } else {
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        else if (criteria.getOperation().equalsIgnoreCase("*")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()),criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        else if (criteria.getOperation().equalsIgnoreCase("**")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), "%" + criteria.getValue() );
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }

        else if (criteria.getOperation().equalsIgnoreCase("#")) {
                // later
            return builder.between(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                    .setWeekDate(1956, 1, MONDAY).build(),new Calendar.Builder().setCalendarType("iso8601")
                    .setWeekDate(2013, 1, MONDAY).build());
        }
        else if (criteria.getOperation().equalsIgnoreCase("##")) {
            // later
            return builder.lessThan(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                    .setWeekDate(2000, 1, MONDAY).build());
        }
        else if (criteria.getOperation().equalsIgnoreCase("###")) {
            // later
            return builder.greaterThan(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                    .setWeekDate(2000, 1, MONDAY).build());
        }
        else if (criteria.getOperation().equalsIgnoreCase("####")) {
            // later
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                    .setWeekDate(2000, 1, MONDAY).build());
        }
        else if (criteria.getOperation().equalsIgnoreCase("#####")) {
            // later
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                    .setWeekDate(2000, 1, MONDAY).build());
        }
        else if (criteria.getOperation().equalsIgnoreCase("######")) {
            // later
            return builder.equal(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                    .setWeekDate(2000, 1, MONDAY).build());
        }
        else if (criteria.getOperation().equalsIgnoreCase("#######")) {
            // later
            return builder.notEqual(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                    .setWeekDate(2000, 1, MONDAY).build());
        }


        return null;
    }
}

