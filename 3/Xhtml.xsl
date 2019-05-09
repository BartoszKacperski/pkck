<xsl:stylesheet version="1.0" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns="http://www.w3.org/1999/xhtml">

    <xsl:output method="xml" doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd" />


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
                    <xsl:attribute name="align">center</xsl:attribute>
                    Baza książek
                </xsl:element>
                <xsl:apply-templates/>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <xsl:template match="books">
        <xsl:element name="div">
            <xsl:element name="table">
                <xsl:attribute name="border">1</xsl:attribute>
                <xsl:attribute name="cellspacing">0</xsl:attribute>
                <xsl:attribute name="cellpadding">5</xsl:attribute>
                <xsl:attribute name="width">80%</xsl:attribute>
                <xsl:attribute name="align">center</xsl:attribute>
                <xsl:attribute name="bgcolor">#7a787b</xsl:attribute>
                <xsl:element name="thead">
                    <xsl:element name="tr">
                        <xsl:element name="td">
                            Tytuł
                        </xsl:element>
                        <xsl:element name="td">
                            Autor
                        </xsl:element>
                        <xsl:element name="td">
                            Kategoria
                        </xsl:element>
                        <xsl:element name="td">
                            Strony
                        </xsl:element>
                        <xsl:element name="td">
                            Ocena
                        </xsl:element>
                        <xsl:element name="td">
                            Data wydania
                        </xsl:element>
                    </xsl:element>
                </xsl:element>
                <xsl:element name="tbody">
                    <xsl:for-each select="book">
                        <xsl:element name="tr">
                            <xsl:element name="td">
                                <xsl:attribute name="bgcolor">#9dadc6</xsl:attribute>
                                <xsl:value-of select="title"/>
                            </xsl:element>
                            <xsl:element name="td">
                                <xsl:attribute name="bgcolor">#9dad7b</xsl:attribute>
                                <xsl:value-of select="author"/>
                            </xsl:element>
                            <xsl:element name="td">
                                <xsl:attribute name="bgcolor">#9dadc6</xsl:attribute>
                                <xsl:value-of select="category"/>
                            </xsl:element>
                            <xsl:element name="td">
                                <xsl:attribute name="bgcolor">#9dad7b</xsl:attribute>
                                <xsl:value-of select="pages"/>
                            </xsl:element>
                            <xsl:element name="td">
                                <xsl:attribute name="bgcolor">#9dadc6</xsl:attribute>
                                <xsl:value-of select="rating"/>
                            </xsl:element>
                            <xsl:element name="td">
                                <xsl:attribute name="bgcolor">#9dad7b</xsl:attribute>
                                <xsl:value-of select="releaseDate"/>
                            </xsl:element>
                        </xsl:element>
                    </xsl:for-each>
                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

  <xsl:template match="stats">
        <xsl:element name="div">
            <xsl:element name="h4">
                <xsl:attribute name="align">left</xsl:attribute>
                Statystki
            </xsl:element>
        </xsl:element>
        <xsl:element name="ul">
          <xsl:element name="li">
            <xsl:text>Data wygenerowania: </xsl:text>
            <xsl:value-of select="date"/>
          </xsl:element>
          <xsl:element name="li">
            <xsl:text>Ilość książek: </xsl:text>
            <xsl:value-of select="booksCount"/>
          </xsl:element>
          <xsl:element name="li">
            <xsl:text>Ilość autorów: </xsl:text>
            <xsl:value-of select="authorsCount"/>
          </xsl:element>
          <xsl:element name="li">
            <xsl:text>Ilość kategori: </xsl:text>
            <xsl:value-of select="categoriesCount"/>
          </xsl:element>
          <xsl:element name="li">
            <xsl:text>Średnia liczba stron: </xsl:text>
            <xsl:value-of select="averagePages"/>
          </xsl:element>
      </xsl:element>
  </xsl:template>

</xsl:stylesheet>
