/**
 * 全局通用js
 */
export default {
    /**
     * @param queryObj 将对象类型转化为请求参数
     */
    transferObjToQueryParams: queryObj => {
        if (queryObj) {
            return Object.keys(queryObj).map(key => key + '=' + queryObj[key]).join('&');
        } else {
            return '';
        }
    },
    /**
     * 注册vue组件
     * @param {Vue} vue 
     */
    registerComponents: vue => {
        
    }
}