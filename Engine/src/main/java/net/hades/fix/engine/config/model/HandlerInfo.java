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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.management.openmbean.*;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import net.hades.fix.engine.config.xml.adapter.BooleanAdapter;

/**
 * Handler references configuration data.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "handler")
@XmlType(name = "HandlerInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class HandlerInfo implements CompositeDataView, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String[] COMPOSITE_DATA_ITEMS;
    private static final String[] COMPOSITE_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] COMPOSITE_DATA_OPEN_TYPES;

    public static CompositeType DataType;

    static {
        try {
            COMPOSITE_DATA_ITEMS = new String[]{"id", "name", "disabled", "nextHandler"};
            COMPOSITE_DATA_ITEMS_DESCRIPTION = new String[]{"Handler System ID", "Handler Definition Reference",
                    "Is Disabled?", "Next Handlers"};
            COMPOSITE_DATA_OPEN_TYPES = new OpenType<?>[]{SimpleType.STRING, SimpleType.STRING, SimpleType.BOOLEAN,
                    SimpleType.STRING};

            DataType = new CompositeType("HandlerInfo", "Handler Data", COMPOSITE_DATA_ITEMS, COMPOSITE_DATA_ITEMS_DESCRIPTION, COMPOSITE_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintWriter pout = new PrintWriter(bout);
            e.printStackTrace(pout);
            pout.flush();
            throw new RuntimeException(bout.toString());
        }
    }

    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "name", required = true)
    private String name;

    @XmlAttribute(name = "disabled")
    @XmlJavaTypeAdapter(BooleanAdapter.class)
    private Boolean disabled;

    @XmlElement(name = "handler")
    @XmlElementWrapper(name = "next")
    private HandlerRefInfo[] handler;

    public HandlerInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public HandlerRefInfo[] getHandler() {
	return handler;
    }

    public void setHandler(HandlerRefInfo[] handler) {
	this.handler = handler;
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
            CompositeData cd = new CompositeDataSupport(xct, COMPOSITE_DATA_ITEMS, new Object[]{id, name, disabled, handler});
            assert ct.isValue(cd);
            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{HandlerInfo[");
        if (id != null) {
            sb.append("id=").append(id);
        }
        if (name != null) {
            sb.append(", name=").append(name);
        }
        if (disabled != null) {
            sb.append(", disabled=").append(disabled);
        }
        if (handler != null) {
            sb.append(", handler=").append(Stream.of(handler).map(p -> p.getId()).collect(Collectors.joining(","))).append("\n");
        }
        sb.append("]}");
        return sb.toString();
    }
}
