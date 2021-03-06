import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_musicfan_artistindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/artist/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("utf-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("X-UA-Compatible"),'content':("IE=edge")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width, initial-scale=1")],-1)
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("description"),'content':("")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("author"),'content':("")],-1)
printHtmlPart(3)
createTagBody(2, {->
createTagBody(3, {->
printHtmlPart(4)
expressionOut.print(artistName)
})
invokeTag('captureTitle','sitemesh',10,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',10,[:],2)
printHtmlPart(5)
expressionOut.print(resource(dir:'css',file:'bootstrap.min.css'))
printHtmlPart(6)
expressionOut.print(resource(dir:'css',file:'jumbotron.css'))
printHtmlPart(6)
expressionOut.print(resource(dir:'css',file:'dashboard.css'))
printHtmlPart(7)
expressionOut.print(resource(dir:'js',file:'bootstrap.min.js'))
printHtmlPart(8)
expressionOut.print(resource(dir:'js',file:'musicfan.js'))
printHtmlPart(9)
expressionOut.print(resource(dir:'js',file:'holder.js'))
printHtmlPart(10)
})
invokeTag('captureHead','sitemesh',23,[:],1)
printHtmlPart(11)
createTagBody(1, {->
printHtmlPart(12)
expressionOut.print(artistName)
printHtmlPart(13)
for( _it1172931506 in (bbresults) ) {
changeItVariable(_it1172931506)
printHtmlPart(14)
expressionOut.print(it.url)
printHtmlPart(15)
expressionOut.print(it.img)
printHtmlPart(16)
expressionOut.print(it.name)
printHtmlPart(17)
expressionOut.print(it.review)
printHtmlPart(18)
expressionOut.print(it.releaseDate)
printHtmlPart(19)
}
printHtmlPart(20)
for( _it1707425529 in (amazonItems) ) {
changeItVariable(_it1707425529)
printHtmlPart(14)
expressionOut.print(it.DetailPageURL)
printHtmlPart(15)
expressionOut.print(it.Image)
printHtmlPart(16)
expressionOut.print(it.Name)
printHtmlPart(21)
expressionOut.print(it.WishList)
printHtmlPart(22)
expressionOut.print(it.Offers)
printHtmlPart(23)
expressionOut.print(it.keyOffers.LowestNewPrice)
printHtmlPart(24)
expressionOut.print(it.keyOffers.LowestUsedPrice)
printHtmlPart(25)
expressionOut.print(it.keyOffers.LowestCollectiblePrice)
printHtmlPart(26)
expressionOut.print(it.Reviews)
printHtmlPart(27)
}
printHtmlPart(28)
expressionOut.print(twitter)
printHtmlPart(15)
expressionOut.print(bbresults.get(0).img)
printHtmlPart(29)
expressionOut.print(artistName)
printHtmlPart(30)
invokeTag('search','gcse',141,[:],-1)
printHtmlPart(31)
})
invokeTag('captureBody','sitemesh',274,[:],1)
printHtmlPart(32)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1429816184000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
