import common from "../js/common.js";

/**
 * API 请求前缀
 */
const apiPrefix = 'http://localhost:8080'

/**
 *  向请求路径中追加请求参数
 * @param {String} url 请求路径
 * @param {Object} params 请求参数
 * @returns 
 */
const appendRequestParams = (url, params) => {
    if (!url) {
        throw new Error('Invalid url');
    }
    if (params) {
        return url = `${apiPrefix + url}?${common.transferObjToQueryParams(params)}`;
    } else {
        return `${apiPrefix + url}?`;
    }
};

export default {
    /**
      * 发送post请求
      * @param {string} url 请求路径
      * @param {object} params get请求参数
      * @param {object} data post请求参数
      * @returns 
      */
    async doPost(url, params, data = {}) {
        url = appendRequestParams(url, params);
        const response = await fetch(url, {
            method: "POST", // *GET, POST, PUT, DELETE, etc.
            headers: {
                "Content-Type": "application/json",
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            //   redirect: "follow", // manual, *follow, error
            //   referrerPolicy: "no-referrer", // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
            body: JSON.stringify(data), // body data type must match "Content-Type" header
        });
        return response.json(); // parses JSON response into native JavaScript objects
    },

    /**
     * 发送get请求
     * @param {string} url 请求路径
     * @param {object} params get请求参数
     * @returns 
     */
    async doGet(url, params) {
        url = appendRequestParams(url, params);
        const response = await fetch(url, {
            method: "GET" // *GET, POST, PUT, DELETE, etc.
        });
        return response.json();
    }
}
