import request from "../request.js";

const apiPrefix = '/lucene'
/**
 * lucene client
 */
export default {
    /**
     * 查询lucene文档列表
     * @returns 所有的文档
     */
    listAllDocuments: () => {
        return request.doGet(`${apiPrefix}/listAllDocuments`)
    }
}