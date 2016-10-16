/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */
package net.hades.fix.engine.config.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.management.openmbean.CompositeData;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.CompositeDataView;
import javax.management.openmbean.CompositeType;
import javax.management.openmbean.OpenDataException;
import javax.management.openmbean.OpenType;
import javax.management.openmbean.SimpleType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Configuration for a handler reference data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "parameter")
@XmlType(name = "HandlerParamInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class HandlerRefInfo implements CompositeDataView, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String[] COMPOSITE_DATA_ITEMS;
    private static final String[] COMPOSITE_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] COMPOSITE_DATA_OPEN_TYPES;

    public static CompositeType DataType;

    static {
        try {
            COMPOSITE_DATA_ITEMS = new String[]{"id"};
            COMPOSITE_DATA_ITEMS_DESCRIPTION = new String[]{"Identifier"};
            COMPOSITE_DATA_OPEN_TYPES = new OpenType<?>[]{SimpleType.STRING};

            DataType = new CompositeType("HandlerRefInfo", "Handler Reference Data", COMPOSITE_DATA_ITEMS,
                    COMPOSITE_DATA_ITEMS_DESCRIPTION, COMPOSITE_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintWriter pout = new PrintWriter(bout);
            e.printStackTrace(pout);
            pout.flush();
            throw new RuntimeException(bout.toString());
        }
    }

    @XmlAttribute(name = "id", required = true)
    private String id;

    public HandlerRefInfo() {
    }

    public HandlerRefInfo(String id) {
        this.id = id;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    @Override
    public CompositeData toCompositeData(CompositeType ct) {
        try {
            List<String> itemNames = new ArrayList<>(ct.keySet());
            List<String> itemDescriptions = new ArrayList<>(itemNames.size());
            List<OpenType<?>> itemTypes = new ArrayList<>();
	    itemNames.stream().map((item) -> {
		itemDescriptions.add(ct.getDescription(item));
		return item;
	    }).forEach((item) -> {
		itemTypes.add(ct.getType(item));
	    });

            CompositeType xct = new CompositeType(ct.getTypeName(),
                    ct.getDescription(),
                    itemNames.toArray(new String[itemNames.size()]),
                    itemDescriptions.toArray(new String[itemDescriptions.size()]),
                    itemTypes.toArray(new OpenType<?>[itemTypes.size()]));
            CompositeData cd = new CompositeDataSupport(xct, COMPOSITE_DATA_ITEMS, new Object[]{id});
            assert ct.isValue(cd);
            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{HandlerParamInfo[");
        if (id != null) {
            sb.append("id=").append(id);
        }
        sb.append("]}");
        return sb.toString();
    }

}
