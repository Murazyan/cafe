package com.sfl.cafe.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sfl.cafe.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type")
    private UserType type;


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

//    @OneToMany(mappedBy = "waiter", cascade = CascadeType.ALL, orphanRemoval = true )
//    @LazyCollection(LazyCollectionOption.FALSE)
//    @JsonIgnore
//    private List<com.sfl.cafe.model.Table> tables;

}
