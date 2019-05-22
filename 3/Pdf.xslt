<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output method="xml"/>
    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master
                        master-name="simple"
                        page-height="29.7cm"
                        page-width="21cm"
                        margin-top="1cm"
                        margin-bottom="1cm"
                        margin-left="2.5cm"
                        margin-right="2.5cm"
                >
                    <fo:region-body margin-top="1.5cm" margin-bottom="1.5cm"/>
                    <fo:region-before extent="0"/>
                    <fo:region-after extent="0"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="simple" font-family="Arial">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:block font-size="2em" text-align="center" font-weight="bold">
                            <xsl:text>Ksiegarnia</xsl:text>
                        </fo:block>
                        <fo:block>
                            <xsl:apply-templates/>
                        </fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>

    <xsl:template match="books">
        <fo:block margin-top="25px">
            <fo:block font-size="1.5em" text-align="center" font-weight="bold" margin-bottom="5px">
                <xsl:text>Książki</xsl:text>
            </fo:block>
            <fo:table width="100%" border="1px solid black" background-color="grey" text-align="center">
                <fo:table-header font-weight="bold" background-color="pink">
                    <fo:table-row>
                        <fo:table-cell border="2px solid black" padding="5px" >
                            <fo:block >
                                <xsl:text>Tytuł</xsl:text>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="2px solid black" padding="5px">
                            <fo:block>
                                <xsl:text>Autor</xsl:text>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="2px solid black" padding="5px">
                            <fo:block>
                                <xsl:text>Kategoria</xsl:text>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="2px solid black" padding="5px">
                            <fo:block>
                                <xsl:text>Strony</xsl:text>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="2px solid black" padding="5px">
                            <fo:block>
                                <xsl:text>Ocena</xsl:text>
                            </fo:block>
                        </fo:table-cell>
                        <fo:table-cell border="2px solid black" padding="5px">
                            <fo:block>
                                <xsl:text>Data wydania</xsl:text>
                            </fo:block>
                        </fo:table-cell>

                    </fo:table-row>
                </fo:table-header>
                <fo:table-body font-size="0.75em">
                    <xsl:for-each select="book">
                        <fo:table-row>
                            <fo:table-cell border="2px solid black" padding="5px">
                                <fo:block>
                                    <xsl:value-of select="title"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="2px solid black" padding="5px">
                                <fo:block>
                                    <xsl:value-of select="author"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="2px solid black" padding="5px">
                                <fo:block>
                                    <xsl:value-of select="category"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="2px solid black" padding="5px">
                                <fo:block>
                                    <xsl:value-of select="pages"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="2px solid black" padding="5px">
                                <fo:block>
                                    <xsl:value-of select="rating"/>
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="2px solid black" padding="5px">
                                <fo:block>
                                    <xsl:value-of select="releaseDate"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </xsl:for-each>
                </fo:table-body>
            </fo:table>
        </fo:block>
    </xsl:template>


    <xsl:template match="stats">
        <fo:block text-align="center" margin-top="50px" margin="10px" background-color="grey">
            <fo:block font-size="3em" font-weight="bold" margin-bottom="5px">
                <xsl:text>Statystki</xsl:text>
            </fo:block>
            <fo:block font-size="1em" padding="5px" color="pink">
                <fo:block>
                    <xsl:text>Liczba książek: </xsl:text>
                    <xsl:value-of select="./booksCount"/>
                </fo:block>
                <fo:block>
                    <xsl:text>Liczba autorów: </xsl:text>
                    <xsl:value-of select="./authorsCount"/>
                </fo:block>
                <fo:block>
                    <xsl:text>Liczba kategori: </xsl:text>
                    <xsl:value-of select="./categoriesCount"/>
                </fo:block>
                <fo:block>
                    <xsl:text>Srednia liczba stron: </xsl:text>
                    <xsl:value-of select="./averagePages"/>
                </fo:block>
                <fo:block>
                    <xsl:text>Data wygenerowania: </xsl:text>
                    <xsl:value-of select="./date"/>
                </fo:block>
            </fo:block>
        </fo:block>
    </xsl:template>

</xsl:stylesheet>
