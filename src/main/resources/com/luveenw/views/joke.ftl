<html>
    <body>
        <blockquote><strong>"${text}"</strong><blockquote>
        <div style="padding-left: 250px;">
            <span><i>--${author}</i></span>
        </div>
        <div>
            <#if (categories)?size != 0>
                <span><b>Categories:</b> <i><#list categories as c>${c}<#sep>, </#sep></#list></i></span>
            <#else>
                <span><i>No categories</i></span>
            </#if>
        </div>
        <div>
            <span><b>ID:</b> <i>${id}</i></span>
        </div>
    </body>
<html>