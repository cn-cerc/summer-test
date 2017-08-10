<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <center>
            <h2>
                <xsl:value-of select="html/head/title" />
                (
                <xsl:value-of select="html/head/system/@code" />
                )
                <h3>
                    <a href="index.xml">返回系统选择</a>
                </h3>
            </h2>
        </center>
        <table border="1" width="100%" bordercolorlight="#000000" bordercolordark="#FFFFFF" bordercolor="#000000"
            cellspacing="0" cellpadding="3">
            <tr bordercolorlight="#000000" bordercolordark="#FFFFFF" bgcolor="#66FF99">
                <td align="center">
                    <b>类别</b>
                </td>
                <td align="center">
                    <b>功能名称</b>
                </td>
                <td align="center">
                    <b>等级</b>
                </td>
                <td align="center">
                    <b>文档号</b>
                </td>
                <td align="center">
                    <b>备注</b>
                </td>
                <td align="center">
                    <b>隐藏否</b>
                </td>
                <td align="center">
                    <b>移除否</b>
                </td>
                <td align="center">
                    <b>更新日期</b>
                </td>
            </tr>
            <xsl:for-each select="html/body/section">
                <xsl:for-each select="item">
                    <tr>
                        <xsl:variable name="count" select="last()" />
                        <xsl:if test="position()=1">
                            <td rowspan="{$count}" align="center" valign="middle">
                                <span>
                                    <xsl:value-of select="../@caption" />
                                </span>
                            </td>
                        </xsl:if>
                        <td>
                            <xsl:element name="a">
                                <xsl:attribute name="href">hrip:<xsl:value-of select="@code" /></xsl:attribute>
                            </xsl:element>
                            <xsl:value-of select="@name" />
                            <br />
                        </td>
                        <td align="center">
                            <xsl:value-of select="@level" />
                            <br />
                        </td>
                        <td align="center">
                            <xsl:element name="a">
                                <xsl:attribute name="href">http://appdocs.sinaapp.com/helpme.php?id=<xsl:value-of
                                    select="@docid" /></xsl:attribute>
                            </xsl:element>
                            <xsl:value-of select="@docid" />
                            <br />
                        </td>
                        <td>
                            <xsl:value-of select="remark" />
                            <br />
                        </td>
                        <td align="center">
                            <xsl:value-of select="@hide" />
                            <br />
                        </td>
                        <td align="center">
                            <xsl:value-of select="@delete" />
                            <br />
                        </td>
                        <td>
                            <xsl:value-of select="@qcdate" />
                            <br />
                        </td>
                    </tr>
                </xsl:for-each>
            </xsl:for-each>
        </table>
        <center>
            <h4>
                版权所有：深圳市华软资讯科技有限公司
                <br />
            </h4>
            <h4>
                <a href="http://www.mimrc.com">http://www.mimrc.com</a>
            </h4>
        </center>
    </xsl:template>
</xsl:stylesheet>