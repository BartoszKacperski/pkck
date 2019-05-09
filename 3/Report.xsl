<xsl:stylesheet version="2.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml"/>

    <xsl:template match="/">
        <xsl:element name="report">
            <xsl:element name="books">
                <xsl:for-each select="root/bookstore/books/book">
                    <xsl:variable name="Id" select="@authorId"/>
                    <xsl:variable name="author" select="/root/bookstore/authors/author[@authorId eq $Id]"/>

                    <xsl:element name="book">
                        <xsl:element name="title">
                            <xsl:value-of select="title"/>
                        </xsl:element>
                        <xsl:element name="author">
                            <xsl:value-of select="$author/firstName"/>
                            <xsl:text></xsl:text>
                            <xsl:value-of select="$author/lastName"/>
                        </xsl:element>
                        <xsl:element name="category">
                            <xsl:value-of select="@category"/>
                        </xsl:element>
                        <xsl:if test="@pages">
                            <xsl:element name="pages">
                                <xsl:value-of select="@pages"/>
                            </xsl:element>
                        </xsl:if>
                        <xsl:element name="rating">
                            <xsl:value-of select="@rating"/>
                        </xsl:element>
                        <xsl:element name="releaseDate">
                            <xsl:value-of select="releaseDate/year"/>
                            <xsl:text>-</xsl:text>
                            <xsl:value-of select="releaseDate/month"/>
                            <xsl:text>-</xsl:text>
                            <xsl:value-of select="releaseDate/day"/>
                        </xsl:element>
                    </xsl:element>
                </xsl:for-each>
            </xsl:element>
            <xsl:element name="stats">
                <xsl:variable name="booksCount" select="count(/root/bookstore/books/book)"/>
                <xsl:variable name="authorsCount" select="count(/root/bookstore/authors/author)"/>

                <xsl:element name="date">
                    <xsl:value-of select="current-dateTime()"/>
                </xsl:element>
                <xsl:element name="booksCount">
                    <xsl:value-of select="$booksCount"/>
                </xsl:element>
                <xsl:element name="authorsCount">
                    <xsl:value-of select="$authorsCount"/>
                </xsl:element>
                <xsl:element name="categoriesCount">
                    <xsl:value-of select="count(distinct-values(/root/bookstore/books/book/@category))"/>
                </xsl:element>
                <xsl:element name="date">
                    <xsl:value-of select="current-dateTime()"/>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>