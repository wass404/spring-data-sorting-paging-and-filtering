package com.example.paginationgridview.specification;

import com.example.paginationgridview.entite.Test;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.*;

import static java.util.Calendar.MONDAY;

public class TestSpecification implements Specification<Test> {

    private SearchCriteria criteria;

    public TestSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }


    @Nullable
    @Override
    public Predicate toPredicate(Root<Test> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
// property equal for Strings Numerics and Dates
        if (criteria.getOperation().equalsIgnoreCase("=")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(builder.lower(root.<String>get(criteria.getKey())), criteria.getValue().toString().toLowerCase());
            } else if (root.get(criteria.getKey()).getJavaType() == Date.class) {
                return builder.equal(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                        .setWeekDate(2000, 1, MONDAY).build());
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }

        }

        // property not equal for Strings Numerics and Dates
        else if (criteria.getOperation().equalsIgnoreCase("<>")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.notLike(builder.lower(root.<String>get(criteria.getKey())), criteria.getValue().toString().toLowerCase());
            } else if (root.get(criteria.getKey()).getJavaType() == Date.class) {
                return builder.notEqual(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                        .setWeekDate(2000, 1, MONDAY).build());
            } else {
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        // property contains only for Strings
        else if (criteria.getOperation().equalsIgnoreCase("contains")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        builder.lower(root.<String>get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
            }
        }

        // property not contains only for Strings
        else if (criteria.getOperation().equalsIgnoreCase("notcontains")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.notLike(
                        builder.lower(root.<String>get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
            }
        }

        // property >= for   Numerics and Dates
        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            if (root.get(criteria.getKey()).getJavaType() == Date.class) {
//                System.out.println(criteria.getValue().toString());
//                System.out.println(new Date());
//                String string = criteria.getValue().toString();
//                DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
//                Date date = null;
//                try {
//                    date = format.parse(string);
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(date);
                OffsetDateTime odt = OffsetDateTime.parse( "2010-11-07T00:00:00.000+01:00");
                // Date date = Date.from( Instant.parse( "2018-11-07T00:00:00.000 +01:00" ));
                Instant instant = odt.toInstant();  // Instant is always in UTC.
                Date date = java.util.Date.from( instant );
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()).as(Date.class), date);
            } else {
                return builder.greaterThanOrEqualTo(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            }
        }
        // property <= for   Numerics and Dates
        else if (criteria.getOperation().equalsIgnoreCase("<=")) {

            if (root.get(criteria.getKey()).getJavaType() == Date.class) {
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                        .setWeekDate(2000, 1, MONDAY).build());
            } else {
                return builder.lessThanOrEqualTo(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            }
        }
        // property > for  Numerics and Dates
        else if (criteria.getOperation().equalsIgnoreCase(">")) {

            if (root.get(criteria.getKey()).getJavaType() == Date.class) {
                return builder.greaterThan(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                        .setWeekDate(2000, 1, MONDAY).build());
            } else {
                return builder.greaterThan(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            }
        }
        // property < for Numerics and Dates
        else if (criteria.getOperation().equalsIgnoreCase("<")) {

            if (root.get(criteria.getKey()).getJavaType() == Date.class) {
                return builder.lessThan(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                        .setWeekDate(2000, 1, MONDAY).build());
            } else {
                return builder.lessThan(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            }
        }
        // property < for Numerics and Dates
        else if (criteria.getOperation().equalsIgnoreCase("between")) {

            if (root.get(criteria.getKey()).getJavaType() == Date.class) {
                return builder.between(root.get(criteria.getKey()).as(Calendar.class), new Calendar.Builder().setCalendarType("iso8601")
                    .setWeekDate(1956, 1, MONDAY).build(),new Calendar.Builder().setCalendarType("iso8601")
                    .setWeekDate(2013, 1, MONDAY).build());

            } else {
                return builder.between(root.get(criteria.getKey()), 0,1000000);
            }
        }

        // property startwith only for Strings

        else if (criteria.getOperation().equalsIgnoreCase("startswith")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        builder.lower(root.<String>get(criteria.getKey())),criteria.getValue().toString().toLowerCase() + "%");
            }
        }
        // property endwith only for Strings

        else if (criteria.getOperation().equalsIgnoreCase("endswith")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        builder.lower(root.<String>get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() );
            }
        }

        return null;
    }
}

