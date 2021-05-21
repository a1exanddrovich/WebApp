package com.epam.web.tag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;

public class PaginationTag implements Tag {

    private PageContext pageContext;
    private Tag parentTag;
    private String commandName;
    private String nextTitle;
    private String previousTitle;
    private int totalPages;
    private int currentPage;

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setNextTitle(String nextTitle) {
        this.nextTitle = nextTitle;
    }

    public void setPreviousTitle(String previousTitle) {
        this.previousTitle = previousTitle;
    }

    @Override
    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    @Override
    public void setParent(Tag tag) {
        this.parentTag = tag;
    }

    @Override
    public Tag getParent() {
        return this.parentTag;
    }

    @Override
    public int doStartTag() {
        return Tag.SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<ul class=\"menu__list pagination\">");
            if (currentPage != 1) {
                currentPage--;
                out.write("<li class=\"menu__list-item\">");
                out.write("<a class=\"menu__list-item\" href = \"controller?command=" + commandName + "&currentPage=" + currentPage + "\">" + previousTitle + "</a >");
                out.write("</li >");
                currentPage++;
            }
            for (int i = 1; i <= totalPages; i++) {
                out.write("<li class=\"menu__list-item\">");
                out.write("<a class=\"menu__list-link\" href=\"controller?command=" + commandName + "&currentPage=" + i + "\">" + i + "</a>");
                out.write("</li>");
            }
            if (currentPage < totalPages) {
                currentPage++;
                out.write("<li class=\"menu__list-item\">");
                out.write("<a class=\"menu__list-item\" href = \"controller?command=" + commandName + "&currentPage=" + currentPage + "\">" + nextTitle + "</a >");
                out.write("</li>");
                currentPage--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Tag.EVAL_PAGE;
    }

    @Override
    public void release() {
    }

}
