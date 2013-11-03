package br.com.dimd.brsaude.model;

import java.io.Serializable;

public abstract class Unidade implements Serializable{

	private String nome;
	private String telefone;
	private String endereco;
	private long latitude;
	private long longitude;
	private String cnes;
	private String especialidades;
	private String bairro;
	private String rpa;
	private String microregiao;
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	/**
	 * @return the endereco
	 */
	public String getEndereco() {
		return endereco;
	}
	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	/**
	 * @return the latitude
	 */
	public long getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public long getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the cnes
	 */
	public String getCnes() {
		return cnes;
	}
	/**
	 * @param cnes the cnes to set
	 */
	public void setCnes(String cnes) {
		this.cnes = cnes;
	}
	/**
	 * @return the especialidades
	 */
	public String getEspecialidades() {
		return especialidades;
	}
	/**
	 * @param especialidades the especialidades to set
	 */
	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}
	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}
	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	/**
	 * @return the rpa
	 */
	public String getRpa() {
		return rpa;
	}
	/**
	 * @param rpa the rpa to set
	 */
	public void setRpa(String rpa) {
		this.rpa = rpa;
	}
	/**
	 * @return the microregiao
	 */
	public String getMicroregiao() {
		return microregiao;
	}
	/**
	 * @param microregiao the microregiao to set
	 */
	public void setMicroregiao(String microregiao) {
		this.microregiao = microregiao;
	}
	
	
}
