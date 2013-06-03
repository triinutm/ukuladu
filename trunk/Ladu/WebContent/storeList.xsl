<?xml version="1.0" encoding="ISO-8859-1"?>
<!-- Edited by XMLSpy® -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <table border="1">
    <tr><th bgcolor='lightgrey'>Ladu</th><th bgcolor='lightgrey'>Kogus</th></tr>
      <xsl:for-each select="itemstorelist/itemstore">
      <tr>
        <td><xsl:value-of select="store/name"/></td>
        <td><xsl:value-of select="itemCount"/></td>
      </tr>
      </xsl:for-each>
      <tr><th bgcolor='lightgrey'>Laohind</th><td><xsl:value-of select="itemstorelist/itemstore/item/storePrice"/></td></tr>
      <tr><th bgcolor='lightgrey'>Myygihind</th><td><xsl:value-of select="itemstorelist/itemstore/item/salePrice"/></td></tr>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>