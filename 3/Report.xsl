<xsl:stylesheet version="2.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml"/>

    <xsl:template match="/">
        <xsl:element name="report">
            <xsl:element name="books">
                <xsl:for-each select="root/bookstore/books/book">
                    <xsl:variable name="Id" select="@authorId"/>
                    <xsl:variable name="author" select="/root/bookstore/authors/author[@authorId eq $Id]"/>
                    <xsl:variable name="category" select="@category"/>

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
                            <xsl:value-of select="$category"/>
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
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>