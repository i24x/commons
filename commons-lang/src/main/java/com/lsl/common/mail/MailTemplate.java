package com.lsl.common.mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class MailTemplate {

    /**
     * 邮件内容
     *
     * @param userName
     * @return
     * @package com.util.promotion
     * @author long.tang
     * @date 2017-2-9
     * @method EdmNurtureTools.getContent()
     * @project h3c_dbs
     */
    public static String getContent(String userName) {
        String content = "";
        content +=
            "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
        content += "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head>";
        content += "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
        content += "<title>Demystifying Email Design</title>";
        content += "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />";
        content +=
            "<meta name=\"viewport\" content=\"initial-scale=1,maximum-scale=1,minimum-scale=1\" /><meta name=\"viewport\" content=\"initial-scale=0.5,maximum-scale=0.5,minimum-scale=0.5\" /></head>";
        content += "<body style=\"margin: 0; padding: 0;\">";
        content +=
            "<table style=\"border-collapse: collapse; border-spacing: 0; margin: 30px auto; border-color: #18b288;\" width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">";
        content += "<tr style=\"display: block; text-align: center; width: 100%;\">";
        content += "<td style=\"display: block; text-align: center; width: 100%;\" valign=\"middle\">";
        content +=
            "<table class=\"flexible\" style=\"border: 3px solid #18b288; border-color: #18b288 #e4001a #e4001a #18b288; border-collapse: collapse; border-spacing: 0;\" width=\"778\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\"><tr><td style=\"padding: 30px 20px 0px 20px;\">";
        content += "<table style=\"display: block; width: 50%; float: left;\"><tr>";
        content +=
            "<td><a style=\"text-decoration: none;\" title=\"DMDlink 1\" href=\"http://unis-huashan.h3c.com/cn/?smt_cp=h3c_2016&amp;smt_pl=header&amp;smt_md=EDM&amp;smt_ct=header\" target=\"_blank\"><img style=\"border: 0;\" src=\"https://h3c.webpower.asia/mailings/6/185/images/logo.png\" alt=\"\" /></a></td></tr></table>";
        content +=
            "<table style=\"display: block; width: 50%; float: right; text-align: right; height: 41px; vertical-align: bottom; padding-top: 23px;\" align=\"right\">";
        content += "<tr style=\"display: block; text-align: right; width: 100%;\" align=\"right\">";
        content +=
            "<td style=\"display: block; text-align: right; width: 100%; min-width: 300px;\" align=\"right\"><a style=\"font-size: 12px; line-height: 18px; float: right; display: block; text-decoration: none; color: #282828;\" title=\"DMDlink 2\" href=\"#\" target=\"_blank\">如果您无法正常查看此邮件，请点击此处。</a></td></tr></table></td></tr><tr><td style=\"position: relative;\"><table><tr>";
        content +=
            "<td><a style=\"text-decoration: none;\" title=\"DMDlink 3\" href=\"http://unis-huashan.h3c.com/cn/?smt_cp=h3c_2016&amp;smt_pl=content&amp;smt_md=EDM&amp;smt_ct=kv\" target=\"_blank\"><img style=\"width: 100%; border: 0;\" src=\"https://h3c.webpower.asia/mailings/6/185/images/bannerall.jpg\" alt=\"\" /></a></td></tr></table></td></tr><tr>";
        content += "<td style=\"padding: 30px 20px 30px 20px;\">";
        content += "<table style=\";float: left; font-size: 16px; line-height: 26px;\">";
        content += "<tr style=\"display: block; text-align: left; width: 100%;\" align=\"left\">";
        content += "<td style=\"display: block; text-align: left; width: 100%;\" align=\"left\">尊敬的" + userName + "：";
        content +=
            "<p style=\"padding: 0; margin: 0;\">当您收到此邮件，表示您已成为我们的会员，如果您不想再收到此类邮件， <a style=\"text-decoration: none; color: #18b288;\" title=\"DMDlink 4\" href=\"#\">请点击退订。</a></p>";
        content +=
            "<p style=\"padding: 0; margin: 0;\">如果您愿意长期接受此邮件，请将 <a style=\"background: #18b288; color: #fff;\" href=\"#\">h3c@edm.h3c.com</a> 添加至您的联系人列表中。</p>";
        content +=
            "<p>如需了解产品及服务的详细信息，请点击<a style=\"text-decoration: none; color: #18b288;\" title=\"DMDlink 5\" href=\"http://unis-huashan.h3c.com/cn/support/resource_center.html?smt_cp=h3c_2016&amp;smt_pl=content&amp;smt_md=EDM&amp;smt_ct=20160719001\">资源中心</a></p></td></tr></table></td></tr><tr>";
        content +=
            "<td style=\"color: #000; padding: 23px 20px; background: #eeeeee; font-size: 16px; line-height: 20px; text-align: center; vertical-align: middle; font-weight: 500;\" align=\"center\" valign=\"middle\">您可以通过以下方式联系我们：</td></tr><tr>";
        content +=
            "<td style=\"padding: 0px 20px 25px 20px; text-align: center; background: #eeeeee; height: 40px; border: none;\">";
        content +=
            "<table class=\"flexible2\" style=\"background-color: #fff; border: 1px solid #18b288;\" width=\"100%\" cellspacing=\"0\"><tr>";
        content +=
            "<td style=\"padding: 10px 0px; border-bottom: 1px solid #18b288; border-right: 1px solid #18b288;\" align=\"center\" width=\"25%\"><img style=\"display: block; margin: 0 auto; border: 0;\" src=\"https://h3c.webpower.asia/mailings/6/185/images/pc.png\" alt=\"\" /></td>";
        content +=
            "<td style=\"padding: 10px 0px; border-bottom: 1px solid #18b288; border-right: 1px solid #18b288;\" align=\"center\" width=\"25%\"><img style=\"border: 0;\" src=\"https://h3c.webpower.asia/mailings/6/185/images/weibo.png\" alt=\"\" /></td>";
        content +=
            "<td style=\"padding: 10px 0px; border-bottom: 1px solid #18b288; border-right: 1px solid #18b288;\" align=\"center\" width=\"25%\"><img style=\"display: block; margin: 0 auto; border: 0;\" src=\"https://h3c.webpower.asia/mailings/6/185/images/tel.png\" alt=\"\" /></td>";
        content +=
            "<td style=\"padding: 10px 0px; border-bottom: 1px solid #18b288;\" align=\"center\" width=\"25%\"><img style=\"border: 0;\" src=\"https://h3c.webpower.asia/mailings/6/185/images/email.png\" alt=\"\" /></td></tr>";
        content += "<tr style=\"border-color: #18b288;\">";
        content +=
            "<td style=\"padding: 10px 0px; border-right: 1px solid #18b288;\" align=\"center\" width=\"25%\"><a style=\"font-size: 14px; line-height: 35px; text-decoration: none; color: #282828;\" title=\"DMDlink 6\" href=\"http://unis-huashan.h3c.com/cn/?smt_cp=h3c_2016&amp;smt_pl=content&amp;smt_md=EDM&amp;smt_ct=20160719002\" target=\"_blank\">unis-huashan.h3c</a></td>";
        content +=
            "<td style=\"padding: 10px 0px; border-right: 1px solid #18b288;\" align=\"center\" width=\"25%\"><a style=\"font-size: 14px; line-height: 35px; color: #282828; text-decoration: none;\" title=\"DMDlink 7\" href=\"http://weibo.com/hpeg\" target=\"_blank\">http://weibo/hpeg</a></td>";
        content +=
            "<td style=\"padding: 10px 0px; border-right: 1px solid #18b288;\" align=\"center\" width=\"25%\"><a style=\"font-size: 14px; line-height: 35px; color: #282828;\" href=\"tel:4006820507\" target=\"_blank\">400-682-0507</a></td>";
        content +=
            "<td style=\"padding: 10px 0px;\" align=\"center\" width=\"25%\"><a style=\"font-size: 14px; line-height: 35px; text-decoration: none; color: #282828;\" title=\"DMDlink 8\" href=\"http://unis-huashan.h3c.com/cn/contact/?smt_cp=h3c_2016&amp;smt_pl=content&amp;smt_md=EDM&amp;smt_ct=20160719003\" target=\"_blank\">邮件咨询</a></td></tr></table></td></tr><tr>";
        content +=
            "<td style=\"padding: 0px 20px 25px 20px; text-align: center; background: #eeeeee; height: 40px;\"><table class=\"flexible2\" width=\"100%\" border=\"0\"><tr>";
        content += "<td style=\"padding: 10px 0px;\" align=\"left\" valign=\"top\" width=\"33%\">";
        content +=
            "<p style=\"padding-left: 40px; font-weight: bold; font-size: 16px; line-height: 48px; margin: 0;\">产品</p>";
        content +=
            "<p style=\"padding: 0 0 0 32px; margin: 0;\"><a style=\"font-size: 14px; line-height: 24px; text-decoration: none; color: #707277;\" title=\"DMDlink 9\" href=\"http://unis-huashan.h3c.com/cn/products/servers/mission-critical.html?smt_cp=h3c_2016&amp;smt_pl=footer&amp;smt_md=EDM&amp;smt_ct=footer_1\" target=\"_blank\"> <strong style=\"color: #18b288;\">●</strong> 关键业务服务器</a></p>";
        content +=
            "<p style=\"padding: 0 0 0 32px; margin: 0;\"><a style=\"font-size: 14px; line-height: 24px; text-decoration: none; color: #707277;\" title=\"DMDlink 10\" href=\"http://unis-huashan.h3c.com/cn/products/servers/industry-standard.html?smt_cp=h3c_2016&amp;smt_pl=footer&amp;smt_md=EDM&amp;smt_ct=footer_2\" target=\"_blank\"><strong style=\"color: #18b288;\">●</strong> 工业标准服务器</a></p>";
        content +=
            "<p style=\"padding: 0 0 0 32px; margin: 0;\"><a style=\"font-size: 14px; line-height: 24px; text-decoration: none; color: #707277;\" title=\"DMDlink 11\" href=\"http://unis-huashan.h3c.com/cn/products/storage/index.html?smt_cp=h3c_2016&amp;smt_pl=footer&amp;smt_md=EDM&amp;smt_ct=footer_3\" target=\"_blank\"><strong style=\"color: #18b288;\">●</strong> 存储</a></p>";
        content +=
            "<p style=\"padding: 0 0 0 32px; margin: 0;\"><a style=\"font-size: 14px; line-height: 24px; text-decoration: none; color: #707277;\" title=\"DMDlink 12\" href=\"http://unis-huashan.h3c.com/cn/products/integrated-system/index.html?smt_cp=h3c_2016&amp;smt_pl=footer&amp;smt_md=EDM&amp;smt_ct=footer_4\" target=\"_blank\"><strong style=\"color: #18b288;\">●</strong> 集成系统</a></p>";
        content +=
            "<p style=\"padding: 0 0 0 32px; margin: 0;\"><a style=\"font-size: 14px; line-height: 24px; text-decoration: none; color: #707277;\" title=\"DMDlink 13\" href=\"http://unis-huashan.h3c.com/cn/products/software/index.html?smt_cp=h3c_2016&amp;smt_pl=footer&amp;smt_md=EDM&amp;smt_ct=footer_5\" target=\"_blank\"><strong style=\"color: #18b288;\">●</strong> 软件</a></p></td>";
        content += "<td style=\"padding: 10px 0px;\" align=\"left\" valign=\"top\" width=\"33%\">";
        content +=
            "<p style=\"padding-left: 60px; font-weight: bold; font-size: 16px; line-height: 48px; margin: 0;\">服务</p>";
        content +=
            "<p style=\"padding: 0 0 0 52px; margin: 0;\"><a style=\"font-size: 14px; line-height: 24px; text-decoration: none; color: #707277;\" title=\"DMDlink 14\" href=\"http://unis-huashan.h3c.com/cn/service/technology-service-consulting.html?smt_cp=h3c_2016&amp;smt_pl=footer&amp;smt_md=EDM&amp;smt_ct=footer_6\" target=\"_blank\"><strong style=\"color: #18b288;\">●</strong> 技术咨询服务</a></p>";
        content +=
            "<p style=\"padding: 0 0 0 52px; margin: 0;\"><a style=\"font-size: 14px; line-height: 24px; text-decoration: none; color: #707277;\" title=\"DMDlink 15\" href=\"http://unis-huashan.h3c.com/cn/service/technology-service-support.html?smt_cp=h3c_2016&amp;smt_pl=footer&amp;smt_md=EDM&amp;smt_ct=footer_7\" target=\"_blank\"><strong style=\"color: #18b288;\">●</strong> 技术支持服务</a></p>";
        content +=
            "<p style=\"padding: 0 0 0 52px; margin: 0;\"><a style=\"font-size: 14px; line-height: 24px; text-decoration: none; color: #707277;\" title=\"DMDlink 16\" href=\"http://unis-huashan.h3c.com/cn/service/financing-capacity.html?smt_cp=h3c_2016&amp;smt_pl=footer&amp;smt_md=EDM&amp;smt_ct=footer_8\" target=\"_blank\"><strong style=\"color: #18b288;\">●</strong> 弹性容量服务</a></p>";
        content +=
            "<p style=\"padding: 0 0 0 52px; margin: 0;\"><a style=\"color: #707277; font-size: 14px; line-height: 24px; text-decoration: none;\" title=\"DMDlink 17\" href=\"http://www.hpe-online.com/hpe/index.html\" target=\"_blank\"><strong style=\"color: #18b288;\">●</strong> 惠普大学</a></p></td>";
        content +=
            "<td style=\"padding: 10px 0px;\" align=\"center\" valign=\"top\" width=\"33%\"><img style=\"padding: 20px 0 0 40px; margin: 0; border: 0;\" src=\"https://h3c.webpower.asia/mailings/6/185/images/ewm.png\" alt=\"惠普微信\" /></td></tr></table></td></tr><tr>";
        content +=
            "<td style=\"padding: 10px 20px 10px 20px;\"><img src=\"https://h3c.webpower.asia/mailings/6/185/images/text.png\" alt=\"\" /></td></tr><tr>";
        content += "<td style=\"padding: 0px 20px 15px 20px;\" align=\"right\">";
        content +=
            "<table style=\";border-top: 1px solid #9b9a9a; text-align: right; padding-top: 15px; width: 100%;\">";
        content += "<tr style=\"display: block; text-align: right; width: 100%; float: right;\" align=\"right\">";
        content +=
            "<img height=\"1\" width=\"1\" src=\"http://tracking.h3c.com:8085/tracking/?u=11092@h3c.com&amp;msg=42B96F07.BFD0.496F.A68E.A8411B35C564.0000.20170119.FAMRDGEHIBUSMHSO@h3c-email.com\">";
        content +=
            "<td style=\"display: block; text-align: right; width: 100%; min-width: 300px;\" align=\"right\"><img style=\"border: 0;\" src=\"https://h3c.webpower.asia/mailings/6/185/images/logob.png\" alt=\"\" /></td>";
        content +=
            "</tr></table></td></tr></table></td></tr></table><p style=\"display: none;\">&nbsp;</p></body></html>";
        return content;
    }

}