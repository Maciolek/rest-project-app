package com.crud.tasks.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "activedatabase")
@Getter
public class ActiveDatabaseActuatorEndpoint {

    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Value("${spring.jpa.database}")
    private String databaseName;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String hibernateDialect;

    @Value("${spring.datasource.username}")
    private String username;

    @ReadOperation
    public String getInfoActiveDatabase() {

        String request = "Activeprofile: " + this.getActiveProfile() + "\n"
                + "Database name: " + this.getDatabaseName() + "\n"
                + "Hibernate dialog: " + this.getHibernateDialect() + "\n"
                + "Database user: " + this.getUsername();

        return request;
    }
}





