/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-12-3
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 */
/*
 分页控件（designed by: DEVIN 2011年02月16日, modified by: northstar）

 模版说明：
 标准中文：<span>共{RecordCount}条 {PageSize}条/页 第{CurrentPage}/{PageCount}页</span> {List}
 标准英文：<span>Page {CurrentPage} of {PageCount} ({RecordCount} items) PageSize：{PageSize}</span> {List}
 使用说明：Global 参数  cn|en 中文|英文
 标记说明：
 {RecordCount}：总记录数，对应参数：RecordCount
 {PageSize}   ：分页数，  对应参数：PageSize
 {CurrentPage}：当前页，  对应参数：CurrentPage
 {PageCount}  ：总页数，无须输入，自动计算
 {List}       ：分页模版，分页详细显示链接
 参数说明：
 RecordCount  ：总记录数
 CurrentPage  ：当前页
 PageSize     ：分页数
 ControlId    ：代码容器ID
 Attach       ：附加参数（以 , 开头，多个以 , 分隔，字符串用''隔开）例：",2"，"2,'中国'"
 Template     ：可按模版说明编辑模版，为空或无则使用标准模版
 Global       ：语言，cn|en 中文|英文
 Callback     ：脚本方法
 */
String.prototype.format = function () {
    var args = arguments;
    return this.replace(/\{(\d+)\}/g, function (m, i) {
        return args[i];
    });
};
String.prototype.urlParameterClear = function () {
    var url = location.href.replace(new RegExp(this + "=[^&]*", "gi"), "").replace(/&&/g, "&").replace(/\?&/, "?");
    return !url.match(/\?/g) ? url + "?" : !url.match(/(&|\?)$/) ? url + "&" : url;

};
String.prototype.urlRequst = function () {
    var url = location.href;
    var str = "[\?&]" + this + "=([^&]*)";
    var re = new RegExp(str, "gi");
    if (!re.test(url)) return "";
    re.exec(url);
    return RegExp.$1;

};
function Pagination(ini) {
    var $ = this;
    for (var o in ini) {
        $[o] = ini[o];
    }
    var en = $.Global && $.Global == 'en';
    $.Template = $.Template ? $.Template : !en ? '<ul class="pull-right"><li class="disabled"><span>共{RecordCount}条 {PageSize}条/页 第{CurrentPage}/{PageCount}页</span></li></ul><ul>{List}</ul>' : '<ul class="pull-right"><li class="disabled"><span>Page {CurrentPage} of {PageCount} ({RecordCount} items) PageSize：{PageSize}</span></li></ul><ul>{List}</ul>';
    $.CurrentPage = parseInt($.Callback ? $.CurrentPage : $.Separator.urlRequst());
    $.CurrentPage = $.CurrentPage || 1;
    $.TotalPags = Math.ceil($.RecordCount / $.PageSize);
    $.RecordCount = parseInt($.RecordCount);

    if ($.TotalPags <= 6 || $.CurrentPage <= 2) {
        $.index = 1;
        $.endPage = 6 > $.TotalPags ? $.TotalPags : 6;
    }
    else {
        if ($.TotalPags - $.CurrentPage <= 3) {
            $.index = $.TotalPags - 5;
            $.endPage = $.TotalPags;
        } else {
            $.index = $.CurrentPage - 2;
            $.endPage = $.CurrentPage + 3;
        }
    }
    var s = [];
    var url = $.Callback ? 'javascript:{0}('.format($.Callback) : '{0}{1}='.format($.Separator.urlParameterClear(), $.Separator);
    var bracket = $.Callback ? ')' : '';
    if ($.CurrentPage > 1) {
        s.push('<li><a href="{0}1{1}{2}" title="{4}" rel="tooltip">&laquo;</a></li><li><a href="{0}{3}{1}{2}" title="{5}" rel="tooltip">&lsaquo;</a></li>'.format(url, $.Callback ? $.Attach : '', bracket, $.CurrentPage - 1, en ? 'First' : '首页', en ? 'Previous' : '上一页'));
    }
    for (var i = $.index; i <= $.endPage; i++) {
        s.push($.CurrentPage == i ? '<li class="disabled"><span>{0}</span></li>'.format(i) : '<li><a href="{0}{3}{1}{2}" title="{4}" rel="tooltip">{3}</a></li>'.format(url, $.Callback ? $.Attach : '', bracket, i, en ? 'Page: {0}'.format(i) : '第{0}页'.format(i)));
    }
    if ($.TotalPags > $.CurrentPage) {
        s.push('<li><a href="{0}{3}{1}{2}" title="{5}" rel="tooltip">&rsaquo;</a></li><li><a href="{0}{6}{1}{2}" title="{4}" rel="tooltip">&raquo;</a></li>'.format(url, $.Callback ? $.Attach : '', bracket, $.CurrentPage + 1, en ? 'End' : '尾页', en ? 'Next' : '下一页', $.TotalPags));
    }

    var html = $.Template;
    html = html.replace("{RecordCount}", $.RecordCount).replace("{PageSize}", $.PageSize).replace("{PageCount}", $.TotalPags).replace("{CurrentPage}", $.CurrentPage).replace('{List}', s.join(''));

    var obj = document.getElementById($.ControlId);
    if (obj) {
        obj.innerHTML = html;
    }
}