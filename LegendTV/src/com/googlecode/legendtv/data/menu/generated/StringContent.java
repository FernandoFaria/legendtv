//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.4-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.08.19 at 05:00:31 PM EDT 
//


package com.googlecode.legendtv.data.menu.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StringContent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StringContent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="dynamicContent" type="{http://www.googlecode.com/p/legendtv/Menus}DynamicContent"/>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StringContent", propOrder = {
    "dynamicContentReference",
    "text"
})
public class StringContent {

    @XmlElement(name = "dynamicContent")
    protected ContentGeneratorReference dynamicContentReference;
    protected String text;

    /**
     * A dynamic content generator reference, which is used to populate 
     * the text at run-time.
     * 
     * @return
     *     possible object is
     *     {@link ContentGeneratorReference }
     *     
     */
    public ContentGeneratorReference getDynamicContentReference() {
        return dynamicContentReference;
    }

    /**
     * Sets the value of the dynamicContentReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentGeneratorReference }
     *     
     */
    public void setDynamicContentReference(ContentGeneratorReference value) {
        this.dynamicContentReference = value;
    }

    /**
     * Static text content.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

}
