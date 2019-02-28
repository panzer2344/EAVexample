package com.azino.eav.model.eav;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attribute_values")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "value_id")
    private Long id;

    @OneToOne(targetEntity = Attribute.class)
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;

    @OneToOne
    @JoinColumn(name = "entity_id")
    private com.azino.eav.model.eav.Entity entity;

    @Column(name = "value")
    private String value;

}
