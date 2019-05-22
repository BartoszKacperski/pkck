<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text"/>

    <xsl:template match="authors">

    </xsl:template>

    <xsl:template match="books">
        <xsl:for-each select="book">
            <xsl:value-of select="title"/>
            <xsl:text>&#xa;</xsl:text>
            <xsl:value-of select="author"/>
            <xsl:text>&#xa;</xsl:text>
            <xsl:text>Data wydania: </xsl:text>
            <xsl:value-of select="releaseDate"/>
            <xsl:text>&#32;</xsl:text>
            <xsl:text>&#xA;</xsl:text>
            <xsl:text>_____________________</xsl:text>
            <xsl:text>&#xA;</xsl:text>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="stats">
        <xsl:variable
                name="booksCount"
                select="booksCount"
        />
        <xsl:variable
                name="authorsCount"
                select="authorsCount"
        />
        <xsl:variable
                name="averagePages"
                select="averagePages"
        />
        <xsl:variable
                name="categoriesCount"
                select="categoriesCount"
        />
        <xsl:variable
                name="date"
                select="date"
        />
        <xsl:text>&#xA;</xsl:text>
        <xsl:text>==================================</xsl:text>
        <xsl:text>&#xA;</xsl:text>
        <xsl:value-of select="concat('Liczba książek: ', $booksCount)"/>
        <xsl:text>&#xA;</xsl:text>
        <xsl:value-of select="concat('Liczba autorów: ', $authorsCount)"/>
        <xsl:text>&#xA;</xsl:text>
        <xsl:value-of select="concat('Srednia liczba stron: ', $averagePages)"/>
        <xsl:text>&#xA;</xsl:text>
        <xsl:value-of select="concat('Liczba kategorii: ', $categoriesCount)"/>
        <xsl:text>&#xA;</xsl:text>
        <xsl:value-of select="concat('Liczba Data: ', substring($date,1,10))"/>
        <xsl:text>&#xA;</xsl:text>
        <xsl:text>==================================</xsl:text>
    </xsl:template>

</xsl:stylesheet>
