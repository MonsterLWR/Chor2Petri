package tag_for_petri;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李炆睿 on 2017/10/14.
 */
public abstract class Tag {
    private List<Attribute> attributes;
    private List<Tag> children;
    private String text;

    public Tag() {
        attributes = new ArrayList<Attribute>();
        children = new ArrayList<Tag>();
    }

    public Tag getChild(String tagName) {
//        根据标签名获取子标签
        for (Tag tag : this.children) {
            if (tag.getName().equals(tagName)) {
                return tag;
            }
        }
        return null;
    }

    public void addAttribute(Attribute attribute) {
        attributes.add(attribute);
    }

    public void setAttribute(Attribute attribute) {
//        设置某个存在的attribute的值
        String key = attribute.getKey();
        Attribute changing_attr = null;
        List<Attribute> attributes = this.getAttributes();
        for (Attribute attr : attributes) {
            if (attr.getKey().equals(key)) {
                changing_attr = attr;
            }
        }
        attributes.remove(changing_attr);
        attributes.add(attribute);
    }

    public void addChild(Tag child) {
        children.add(child);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Tag> getChildren() {
        return children;
    }

    public void setChildren(List<Tag> children) {
        this.children = children;
    }

    public abstract String getName();

    @Override
    public String toString() {
        return getName() + "{" +
                "attributes=" + attributes +
                ", children=" + children +
                ", text=" + text +
                '}';
    }

    private void construct_element(Element element, Tag tag) {
        //        为element添加tag的属性与文本
        //        添加属性
        for (Attribute attribute : tag.getAttributes()) {
            element.addAttribute(attribute.getKey(), attribute.getValue());
        }
        //        添加标签文本
        if(tag.getText() != null)
            element.addText(tag.getText());
        //        递归添加子标签
        for (Tag sub_tag : tag.getChildren()) {
            Element child_element = element.addElement(sub_tag.getName());
            construct_element(child_element, sub_tag);
        }
    }

    public Element toElement() {
        //        转换标签为dom4j的element对象
        Element relative_root = DocumentHelper.createElement(this.getName());
        construct_element(relative_root, this);

        return relative_root;
    }
}


