//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.4-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2007.08.19 at 05:00:31 PM EDT 
//


package com.googlecode.legendtv.data.menu.generated;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PlacementType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PlacementType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="top-left"/>
 *     &lt;enumeration value="top-middle"/>
 *     &lt;enumeration value="top-right"/>
 *     &lt;enumeration value="center-left"/>
 *     &lt;enumeration value="center-middle"/>
 *     &lt;enumeration value="center-right"/>
 *     &lt;enumeration value="bottom-left"/>
 *     &lt;enumeration value="bottom-middle"/>
 *     &lt;enumeration value="bottom-right"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PlacementType")
@XmlEnum
public enum PlacementType {

    @XmlEnumValue("top-left")
    TOP_LEFT("top-left"),
    @XmlEnumValue("top-middle")
    TOP_MIDDLE("top-middle"),
    @XmlEnumValue("top-right")
    TOP_RIGHT("top-right"),
    @XmlEnumValue("center-left")
    CENTER_LEFT("center-left"),
    @XmlEnumValue("center-middle")
    CENTER_MIDDLE("center-middle"),
    @XmlEnumValue("center-right")
    CENTER_RIGHT("center-right"),
    @XmlEnumValue("bottom-left")
    BOTTOM_LEFT("bottom-left"),
    @XmlEnumValue("bottom-middle")
    BOTTOM_MIDDLE("bottom-middle"),
    @XmlEnumValue("bottom-right")
    BOTTOM_RIGHT("bottom-right");
    private final String value;

    PlacementType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PlacementType fromValue(String v) {
        for (PlacementType c: PlacementType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
