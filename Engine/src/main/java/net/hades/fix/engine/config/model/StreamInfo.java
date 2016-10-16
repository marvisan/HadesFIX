/*
 *   Copyright (c) 2006-2016 Marvisan Pty. Ltd. All rights reserved.
 *               Use is subject to license terms.
 */

/*
 * ChainInfo.java
 *
 * $Id: StreamInfo.java,v 1.5 2011-03-17 07:36:01 vrotaru Exp $
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
import javax.management.openmbean.TabularData;
import javax.management.openmbean.TabularDataSupport;
import javax.management.openmbean.TabularType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Configuration data for a stream of handlers. There are two specializations for a stream chain:
 * 
 * @author <a href="mailto:support@marvisan.com">Support Team</a>
 */
@XmlRootElement(name = "stream")
@XmlType(name = "StreamInfo")
@XmlAccessorType(XmlAccessType.NONE)
public abstract class StreamInfo implements CompositeDataView, Serializable {

    private static final long serialVersionUID = 1L;
    
    protected String id;
    protected String type;
    protected HandlerInfo[] handlers;

    public StreamInfo() {
    }

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(name = "type")
    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    @XmlElementWrapper(name = "handlers")
    @XmlElementRef
    public HandlerInfo[] getHandlers() {
        return handlers;
    }

    public void setHandlers(HandlerInfo[] handlers) {
        this.handlers = handlers;
    }

    private static final String[] COMPOSITE_DATA_ITEMS;
    private static final String[] COMPOSITE_DATA_ITEMS_DESCRIPTION;
    private static final TabularType TABULAR_HANDLERS_TYPE;
    private static final TabularData TABULAR_HANDLERS;
    private static final OpenType<?>[] COMPOSITE_DATA_OPEN_TYPES;
    private static final String[] TABULAR_HANDLERSS_INDEX;

    public static CompositeType DataType;

    static {
        try {
	    TABULAR_HANDLERSS_INDEX = new String[]{"id"};
            COMPOSITE_DATA_ITEMS = new String[]{"id", "type", "handlers"};
            COMPOSITE_DATA_ITEMS_DESCRIPTION = new String[]{"Stream System Identifier", "Type of stream (cons, prod)", "List of handlers"};
	    TABULAR_HANDLERS_TYPE = new TabularType("HandlersInfo", "List of Handlers", HandlerInfo.DataType, TABULAR_HANDLERSS_INDEX);
            TABULAR_HANDLERS = new TabularDataSupport(TABULAR_HANDLERS_TYPE);
            COMPOSITE_DATA_OPEN_TYPES = new OpenType<?>[]{SimpleType.STRING, TABULAR_HANDLERS_TYPE};

            DataType = new CompositeType("StreamInfo", "Stream Data", COMPOSITE_DATA_ITEMS,
                    COMPOSITE_DATA_ITEMS_DESCRIPTION, COMPOSITE_DATA_OPEN_TYPES);
        } catch (OpenDataException e) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            PrintWriter pout = new PrintWriter(bout);
            e.printStackTrace(pout);
            pout.flush();
            throw new RuntimeException(bout.toString());
        }
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
            for (HandlerInfo handler : handlers) {
                TABULAR_HANDLERS.put(handler.toCompositeData(HandlerInfo.DataType));
            }
            CompositeData cd = new CompositeDataSupport(xct, COMPOSITE_DATA_ITEMS, new Object[]{id, TABULAR_HANDLERS});
            assert ct.isValue(cd);

            return cd;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
