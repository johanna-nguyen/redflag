package com.project.redflag.report.specification;

import com.project.redflag.report.entity.Report;
import org.springframework.data.jpa.domain.Specification;

public class ReportSpecification {

    public static Specification<Report> hasFirstName(String firstName) {
        return (root, query, cb) ->
                firstName == null ? null :
                        cb.like(cb.lower(root.get("firstName")),
                                "%" + firstName.toLowerCase() + "%");
    }

    public static Specification<Report> hasLastName(String lastName) {
        return (root, query, cb) ->
                lastName == null ? null :
                        cb.like(cb.lower(root.get("lastName")),
                                "%" + lastName.toLowerCase() + "%");
    }

}
