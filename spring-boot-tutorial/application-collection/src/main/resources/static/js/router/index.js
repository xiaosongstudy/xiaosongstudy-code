import common from "../common.js";
/**
 * 路由js
 */
export default {

    /**
     * 获取路径参数
     * @param {string} path 请求路径
     */
    pathVariables: path => {

    },
    /**
     * 路由到指定路径
     * @param {string} path 路由路径
     * @param {object} params 请求参数
     * @param {string} title 新开页面标题
     */
    push: (path, params, title) => {
        let url = params ? `${path}?${common.transferObjToQueryParams(params)}` : path
        if (title) {
            window.open(url, title)
        } else {
            window.open(url, '_self')
        }
    },
    /**
     * 获取路由参数，如果参数名称为空，则会将所有参数封装为一个对象
     * @param {string} variable 参数名称
     */
    params: variable => {
        return getQueryVariable(variable)
    }
}

/**
   * 获取请求路径参数
   * @param {string} variable 参数名称
   * @returns 
   */
const getQueryVariable = variable => {
    let query = window.location.search.substring(1);
    let vars = query.split("&");
    let paramObj = {}
    for (let i = 0; i < vars.length; i++) {
        let pair = vars[i].split("=");
        if (variable) {
            if (pair[0] == variable) { return pair[1]; }
        } else {
            paramObj[pair[0]] = pair[1];
        }
    }
    return paramObj;
}

/**
 * 网站后缀
 */
const suffix = /.[a-z]+/

/**
 * 路由正则匹配规则
 */
const route = /(\/[a-zA-Z\-]+)+(\/:(\w+)(\??)(\(([^)]+)\))?)?/

const test = '/test/api/123/:type'

console.log('test', test.match(route));