<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet
        xmlns:xsl=
                "http://www.w3.org/1999/XSL/Transform"
        version="1.0"
        >
    <xsl:template match="/">
        <html><body>
            Hello
            <xsl:apply-templates/>
        </body></html>
    </xsl:template>

    <xsl:template match="/ARTICLE/TITLE">
        <h1 align="center">
            <xsl:apply-templates/> </h1>
    </xsl:template>

</xsl:stylesheet>