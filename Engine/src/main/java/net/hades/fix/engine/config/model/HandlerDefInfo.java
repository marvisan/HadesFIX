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
import javax.management.openmbean.*;
import javax.xml.bind.annotation.*;

/**
 * Configuration for a processing handler.
 *
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "handlerDef")
@XmlType(name = "HandlerDefInfo")
@XmlAccessorType(XmlAccessType.NONE)
public class HandlerDefInfo implements CompositeDataView, Serializable {

    private static final long serialVersionUID = 1L;

    private static final String[] COMPOSITE_DATA_ITEMS;
    private static final String[] COMPOSITE_DATA_ITEMS_DESCRIPTION;
    private static final OpenType<?>[] COMPOSITE_DATA_OPEN_TYPES;
    private static final TabularType TABULAR_HANDLER_PARAMS_TYPE;
    private static final TabularData TABULAR_HANDLER_PARAMS;
    private static final String[] HANDLER_INDEX;

    public static CompositeType DataType;

    static {
        try {
            HANDLER_INDEX = new String[]{"name"};
            COMPOSITE_DATA_ITEMS = new String[]{"name", "implClass", "parameters"};
            COMPOSITE_DATA_ITEMS_DESCRIPTION = new String[]{"Handler name", "Handler Implementation Class", "Handler Parameters"};
            TABULAR_HANDLER_PARAMS_TYPE = new TabularType("HandlerParameters", "List of Handler Parameters", HandlerParamInfo.DataType, HANDLER_INDEX);
            TABULAR_HANDLER_PARAMS = new TabularDataSupport(TABULAR_HANDLER_PARAMS_TYPE);
            COMPOSITE_DATA_OPEN_TYPES = new OpenType<?>[]{SimpleType.STRING, SimpleType.STRING, TABULAR_HANDLER_PARAMS_TYPE};

            DataType = new CompositeType("HandlerDefInfo", "Handler Definition Data", COMPOSITE_DATA_ITEMS,
                    COMPOSITE_DATA_ITEMS_DESCRIPTION, COMPOSITE_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintWriter pout = new PrintWriter(bout);
            e.printStackTrace(pout);
            pout.flush();
            throw new RuntimeException(bout.toString());
        }
    }

    private String name;

    private String implClass;

    private HandlerParamInfo[] parameters;

    public HandlerDefInfo() {
    }

    @XmlAttribute(name = "name", required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "implClass", required = true)
    public String getImplClass() {
        return implClass;
    }

    public void setImplClass(String implClass) {
        this.implClass = implClass;
    }

    @XmlElementWrapper(name = "parameters")
    @XmlElementRef()
    public HandlerParamInfo[] getParameters() {
        return parameters;
    }

    public void setParameters(HandlerParamInfo[] parameters) {
        this.parameters = parameters;
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
            for (HandlerParamInfo parameter : parameters) {
                TABULAR_HANDLER_PARAMS.put(parameter.toCompositeData(HandlerParamInfo.DataType));
            }
            CompositeData cd = new CompositeDataSupport(xct, COMPOSITE_DATA_ITEMS, new Object[]{name,
                    implClass,
                    TABULAR_HANDLER_PARAMS});
            assert ct.isValue(cd);

            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{HandlerDefInfo[");
        if (name != null) {
            sb.append("name=").append(name);
        }
        if (implClass != null) {
            sb.append(",implClass=").append(implClass);
        }
        if (parameters != null && parameters.length > 0) {
            sb.append("parameters=").append("\n");
            for (HandlerParamInfo parameter : parameters) {
                sb.append(parameter.toString()).append("\n");
            }
        }
        sb.append("]}");
        return sb.toString();
    }

}
