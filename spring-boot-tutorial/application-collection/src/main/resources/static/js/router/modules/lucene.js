/**
 * lucene 路由模块
 */
export default [
    {
        // 列表
        path: '/lucene/lucene-list',
    },
    {
        // 新增/修改
        path: '/lucene/lucene-mod/:queryType/:refcode?',
    },
    {
        // 详情
        path: '/lucene/lucene-detail/:refcode'
    }
]
