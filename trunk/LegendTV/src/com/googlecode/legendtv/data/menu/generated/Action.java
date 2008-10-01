//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.4-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.08.19 at 05:00:31 PM EDT 
//


package com.googlecode.legendtv.data.menu.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * A representation of the action that a single option in an OptionList performs.
 * 
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Action")
public class Action {

    @XmlAttribute
    protected ActionType type;
    @XmlAttribute
    protected String target;

    /**
     * The type of this action, either a menu or a method invocation.
     * 
     * @return
     *     possible object is
     *     {@link ActionType }
     *     
     */
    public ActionType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionType }
     *     
     */
    public void setType(ActionType value) {
        this.type = value;
    }

    /**
     * <p>The target of the action. This is a menu name if this is a menu action, or a
     * class and method name if this is a method action.</p>	  					
     * 
     * <p>The class and method name should be in form of:
     * <br />&nbsp;&nbsp;&nbsp;{package name}.{method name}</p>
     * 
     * <p>So, for example, if this is was a method action that should trigger the menuSelect()
     * method on the org.googlecode.legendtv.DummyClass class, then the target name would be:
     * <br />&nbsp;&nbsp;&nbsp;org.googlecode.legendtv.DummyClass.menuSelect
     * </p>
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarget(String value) {
        this.target = value;
    }

}
