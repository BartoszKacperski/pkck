<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="http://www.w3.org/2000/svg">
    <xsl:template match="/">
        <svg>
            <xsl:call-template name="Title"/>
            <xsl:call-template name="Chart"/>
        </svg>
    </xsl:template>

    <xsl:template name="Title">
        <svg width="1500" height="500">
            <text id="Title" x="350" y="50" font-family="Arial" font-size="25">
                Statystyka filmów
            </text>
        </svg>
    </xsl:template>

    <xsl:template name="Chart">
        <svg>
            <g transform="translate(0 50)">
                <line x1="200" y1="450" x2="700" y2="450" stroke="black" stroke-width="2"/>
                <line x1="200" y1="450" x2="200" y2="0" stroke="black" stroke-width="2"/>

                <line x1="210" y1="350" x2="190" y2="350" stroke="black" stroke-width="2"/>
                <line x1="210" y1="250" x2="190" y2="250" stroke="black" stroke-width="2"/>
                <line x1="210" y1="150" x2="190" y2="150" stroke="black" stroke-width="2"/>
                <line x1="210" y1="50" x2="190" y2="50" stroke="black" stroke-width="2"/>

                <line x1="210" y1="350" x2="700" y2="350" stroke="grey" stroke-width="1"/>
                <line x1="210" y1="250" x2="700" y2="250" stroke="grey" stroke-width="1"/>
                <line x1="210" y1="150" x2="700" y2="150" stroke="grey" stroke-width="1"/>
                <line x1="210" y1="50" x2="700" y2="50" stroke="grey" stroke-width="1"/>

                <text x="170" y="340" fill="#1d4225" style="font-weight: bold;">5</text>
                <text x="170" y="240" fill="#1d4225" style="font-weight: bold;">10</text>
                <text x="170" y="140" fill="#1d4225" style="font-weight: bold;">15</text>
                <text x="170" y="40" fill="#1d4225" style="font-weight: bold;">20</text>

                <text x="260" y="465" fill="#1d4225" style="font-weight: bold;">Liczba książek</text>
                <text x="410" y="465" fill="#1d4225" style="font-weight: bold;">Liczba autorów</text>
                <text x="560" y="465" fill="#1d4225" style="font-weight: bold;">Liczba kategorii</text>


                <rect fill="blue" x="300">
                    <xsl:attribute name="y">
                        <xsl:value-of select="449-(//report/stats/booksCount)* 20"/>
                    </xsl:attribute>
                    <xsl:attribute name="height">
                        <xsl:value-of select="(//report/stats/booksCount) * 20"/>
                    </xsl:attribute>
                    <animate attributeName="width" attributeType="XML" begin="0s" dur="1s" from="0" repeatCount="1"
                             fill="freeze">
                        <xsl:attribute name="to">25</xsl:attribute>
                    </animate>
                </rect>

                <rect fill="blue" x="450">
                    <xsl:attribute name="y">
                        <xsl:value-of select="449-(//report/stats/authorsCount)* 20"/>
                    </xsl:attribute>
                    <xsl:attribute name="height">
                        <xsl:value-of select="(//report/stats/authorsCount) * 20"/>
                    </xsl:attribute>
                    <animate attributeName="width" attributeType="XML" begin="0s" dur="1s" from="0" repeatCount="1"
                             fill="freeze">
                        <xsl:attribute name="to">25</xsl:attribute>
                    </animate>
                </rect>

                <rect fill="blue" x="600">
                    <xsl:attribute name="y">
                        <xsl:value-of select="449-(//report/stats/categoriesCount)* 20"/>
                    </xsl:attribute>
                    <xsl:attribute name="height">
                        <xsl:value-of select="(//report/stats/categoriesCount) * 20"/>
                    </xsl:attribute>
                    <animate attributeName="width" attributeType="XML" begin="0s" dur="1s" from="0" repeatCount="1"
                             fill="freeze">
                        <xsl:attribute name="to">25</xsl:attribute>
                    </animate>
                </rect>
            </g>


            <script>
                function hideSVG() {
                var style = document.getElementById("additionalStatistics").style.display;
                if(style === "none")
                document.getElementById("additionalStatistics").style.display = "block";
                else
                document.getElementById("additionalStatistics").style.display = "none";
                }
            </script>

            <g>
                <rect x="1130" y="5" rx="5" ry="5" height="30" width="300" fill="grey"/>
                <text onclick="hideSVG()" x="1150" y="25" font-family="Verdana" font-size="15" fill="lightblue">Pokaż/Ukryj dodatkowe statystki
                </text>

            </g>

            <g id="additionalStatistics" class="pie" transform="translate(800 50)" display="none">
                <xsl:variable name="horrors" select="count(//report/books/book[category='horror'])"/>
                <xsl:variable name="dramas" select="count(//report/books/book[category='dramat'])"/>
                <xsl:variable name="actions" select="count(//report/books/book[category='akcja'])"/>
                <xsl:variable name="criminals" select="count(//report/books/book[category='kryminał'])"/>
                <xsl:variable name="novels" select="count(//report/books/book[category='powieść'])"/>

                <line x1="200" y1="450" x2="700" y2="450" stroke="black" stroke-width="2"/>
                <line x1="200" y1="450" x2="200" y2="0" stroke="black" stroke-width="2"/>

                <line x1="210" y1="350" x2="190" y2="350" stroke="black" stroke-width="2"/>
                <line x1="210" y1="250" x2="190" y2="250" stroke="black" stroke-width="2"/>
                <line x1="210" y1="150" x2="190" y2="150" stroke="black" stroke-width="2"/>
                <line x1="210" y1="50" x2="190" y2="50" stroke="black" stroke-width="2"/>

                <line x1="210" y1="350" x2="700" y2="350" stroke="grey" stroke-width="1"/>
                <line x1="210" y1="250" x2="700" y2="250" stroke="grey" stroke-width="1"/>
                <line x1="210" y1="150" x2="700" y2="150" stroke="grey" stroke-width="1"/>
                <line x1="210" y1="50" x2="700" y2="50" stroke="grey" stroke-width="1"/>

                <text x="170" y="340" fill="#1d4225" style="font-weight: bold;">3</text>
                <text x="170" y="240" fill="#1d4225" style="font-weight: bold;">6</text>
                <text x="170" y="140" fill="#1d4225" style="font-weight: bold;">9</text>
                <text x="170" y="40" fill="#1d4225" style="font-weight: bold;">12</text>

                <text x="260" y="465" fill="#1d4225" style="font-weight: bold;">Horrory</text>
                <text x="340" y="465" fill="#1d4225" style="font-weight: bold;">Dramaty</text>
                <text x="420" y="465" fill="#1d4225" style="font-weight: bold;">Powieści</text>
                <text x="500" y="465" fill="#1d4225" style="font-weight: bold;">Kryminały</text>
                <text x="600" y="465" fill="#1d4225" style="font-weight: bold;">Akcji</text>

                <rect fill="lightblue" x="280">
                    <xsl:attribute name="y">
                        <xsl:value-of select="449 - $horrors* 33"/>
                    </xsl:attribute>
                    <xsl:attribute name="height">
                        <xsl:value-of select="$horrors * 33"/>
                    </xsl:attribute>
                    <animate attributeName="width" attributeType="XML" begin="0s" dur="1s" from="0" repeatCount="1"
                             fill="freeze">
                        <xsl:attribute name="to">25</xsl:attribute>
                    </animate>
                </rect>

                <rect fill="lightblue" x="360">
                    <xsl:attribute name="y">
                        <xsl:value-of select="449 - $dramas* 33"/>
                    </xsl:attribute>
                    <xsl:attribute name="height">
                        <xsl:value-of select="$dramas * 33"/>
                    </xsl:attribute>
                    <animate attributeName="width" attributeType="XML" begin="0s" dur="1s" from="0" repeatCount="1"
                             fill="freeze">
                        <xsl:attribute name="to">25</xsl:attribute>
                    </animate>
                </rect>

                <rect fill="lightblue" x="440">
                    <xsl:attribute name="y">
                        <xsl:value-of select="449 - $novels* 33"/>
                    </xsl:attribute>
                    <xsl:attribute name="height">
                        <xsl:value-of select="$novels * 33"/>
                    </xsl:attribute>
                    <animate attributeName="width" attributeType="XML" begin="0s" dur="1s" from="0" repeatCount="1"
                             fill="freeze">
                        <xsl:attribute name="to">25</xsl:attribute>
                    </animate>
                </rect>

                <rect fill="lightblue" x="520">
                    <xsl:attribute name="y">
                        <xsl:value-of select="449 - $criminals* 33"/>
                    </xsl:attribute>
                    <xsl:attribute name="height">
                        <xsl:value-of select="$criminals * 33"/>
                    </xsl:attribute>
                    <animate attributeName="width" attributeType="XML" begin="0s" dur="1s" from="0" repeatCount="1"
                             fill="freeze">
                        <xsl:attribute name="to">25</xsl:attribute>
                    </animate>
                </rect>

                <rect fill="lightblue" x="600">
                    <xsl:attribute name="y">
                        <xsl:value-of select="449 - $actions* 33"/>
                    </xsl:attribute>
                    <xsl:attribute name="height">
                        <xsl:value-of select="$actions * 33"/>
                    </xsl:attribute>
                    <animate attributeName="width" attributeType="XML" begin="0s" dur="1s" from="0" repeatCount="1"
                             fill="freeze">
                        <xsl:attribute name="to">25</xsl:attribute>
                    </animate>
                </rect>

                <text x="285" y="435" fill="#1d4225" style="font-weight: bold;">
                    <xsl:value-of select="$horrors"/>
                </text>
                <text x="370" y="435" fill="#1d4225" style="font-weight: bold;">
                    <xsl:value-of select="$dramas"/>
                </text>
                <text x="450" y="435" fill="#1d4225" style="font-weight: bold;">
                    <xsl:value-of select="$novels"/>
                </text>
                <text x="530" y="435" fill="#1d4225" style="font-weight: bold;">
                    <xsl:value-of select="$criminals"/>
                </text>
                <text x="610" y="435" fill="#1d4225" style="font-weight: bold;">
                    <xsl:value-of select="$actions"/>
                </text>

            </g>
        </svg>
    </xsl:template>
</xsl:stylesheet>