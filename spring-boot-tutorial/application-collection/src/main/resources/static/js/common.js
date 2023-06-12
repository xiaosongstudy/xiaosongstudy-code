
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
    }
}