<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <xsl:element name="html">
            <xsl:attribute name="lang">pl</xsl:attribute>
            <xsl:element name="head">
                <xsl:element name="title">
          Baza książek
                </xsl:element>
            </xsl:element>
            <xsl:element name="body">
                <xsl:element name="h1">
          Baza książek
                </xsl:element>
                <xsl:apply-templates/>
            </xsl:element>
        </xsl:element>
    </xsl:template>


</xsl:stylesheet>
