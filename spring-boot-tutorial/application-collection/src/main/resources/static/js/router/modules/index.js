import lucene from './lucene.js'

/**
 * 全部路由
 */
export const routes = [
    ...lucene
]

/**
 * 路由路径
 */
export const routePaths = Array.from(new Set(routes.map(route => route.path)))