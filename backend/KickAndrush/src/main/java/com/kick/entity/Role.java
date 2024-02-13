package com.kick.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="roles")
@NoArgsConstructor
@Getter
@Setter
public class Role {
 	@Id
    @Column(name="role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

}
