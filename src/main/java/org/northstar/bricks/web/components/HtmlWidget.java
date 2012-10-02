package org.northstar.bricks.web.components;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;

import com.google.sitebricks.Renderable;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.SAXReader;

import com.google.sitebricks.Respond;
import com.google.sitebricks.StringBuilderRespond;
import com.google.sitebricks.rendering.control.WidgetChain;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-10-2
 * Time: 上午11:00
 * To change this template use File | Settings | File Templates.
 */
public abstract class HtmlWidget implements Renderable {
    protected final WidgetChain chain;

    public HtmlWidget(WidgetChain chain)
    {
        this.chain = chain;
    }

    @Override
    public void render(Object bound, Respond respond)
    {
        Document document = getSourceDocument(bound);
        transform(document);
        respond.write(toHtml(document.getRootElement()));
    }

    protected abstract void transform(Document document);

    protected Document getSourceDocument(Object bound)
    {
        StringBuilderRespond builderRespond = new StringBuilderRespond(bound);
        chain.render(bound, builderRespond);
        String string = builderRespond.toString();

        SAXReader reader = new SAXReader();
        Document document;
        try
        {
            document = reader.read(new StringReader(string));
        }
        catch (DocumentException e)
        {
            throw new RuntimeException(e);
        }
        return document;
    }

    protected String toHtml(Element select)
    {
        StringWriter writer = new StringWriter();
        HTMLWriter html = new HTMLWriter(writer);
        try
        {
            html.write(select);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        String string = writer.toString();
        return string;
    }

    @Override
    public <T extends Renderable> Set<T> collect(Class<T> clazz)
    {
        return Collections.emptySet();
    }
}
