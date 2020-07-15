package com.example.demo.entitys;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ctnuocdattiec")
public class CTNuocDatTiec {
	@Id
	private String id;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ttdattiecid",referencedColumnName = "id")
	private TTDatTiec ttDatTiec;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "monid",referencedColumnName = "id")
	private Mon mon;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TTDatTiec getTtDatTiec() {
		return ttDatTiec;
	}

	public void setTtDatTiec(TTDatTiec ttDatTiec) {
		this.ttDatTiec = ttDatTiec;
	}

	public Mon getMon() {
		return mon;
	}

	public void setMon(Mon mon) {
		this.mon = mon;
	}

	public CTNuocDatTiec() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
