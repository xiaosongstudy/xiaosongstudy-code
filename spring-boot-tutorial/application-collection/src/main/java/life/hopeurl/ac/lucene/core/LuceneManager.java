package life.hopeurl.ac.lucene.core;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * lucene管理器
 *
 * @author shiping.song
 * @date 2023/6/12 23:49
 */
@Slf4j
public class LuceneManager {

    /**
     * 索引阅读器
     */
    private IndexReader indexReader;

    /**
     * 索引生成器
     */
    private IndexWriter indexWriter;

    /**
     * 索引搜索器
     */
    private IndexSearcher indexSearcher;

    /**
     * 索引仓库地址
     */
    private final String indexPath;

    public LuceneManager(String indexPath, Mode mode, Analyzer analyzer) {
        assert mode != null;
        try (Directory directory = FSDirectory.open(new File(indexPath).toPath())) {
            this.indexPath = indexPath;
            switch (mode) {
                case ADD: {
                    IndexWriterConfig config = new IndexWriterConfig(Objects.nonNull(analyzer) ? analyzer : new StandardAnalyzer());
                    indexWriter = new IndexWriter(directory, config);
                }
                break;
                case MOD:
                    break;
                case DELETE:
                    break;
                case SEARCH: {
                    indexReader = DirectoryReader.open(directory);
                }
                break;
                default:
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LuceneManager(String indexPath, Mode mode) {
        this(indexPath, mode, null);
    }

    /**
     * 检索lucene中所有的文档
     *
     * @return lucene中所有的文档
     * @date 2023/6/13 00:19
     */
    @SneakyThrows
    public List<Document> searchAllDocuments() {
        // 获取文档总数
        int numDocs = indexReader.numDocs();
        if (numDocs > 0) {
            List<Document> documents = new ArrayList<>(numDocs + 1);
            // 获取每个文档
            for (int i = 0; i < numDocs; i++) {
                documents.add(indexReader.document(i));
            }
            return documents;
        }
        indexReader.close();
        return Collections.emptyList();
    }

    /**
     * 操作模式
     */
    enum Mode {
        /**
         * 查询
         */
        SEARCH,
        /**
         * 新增
         */
        ADD,
        /**
         * 删除
         */
        DELETE,
        /**
         * 修改
         */
        MOD,
        /**
         * 分词
         */
        PARTICIPLE,
        /**
         * 前缀提示
         * TODO：待完成功能
         */
        SUGGEST
    }
}
