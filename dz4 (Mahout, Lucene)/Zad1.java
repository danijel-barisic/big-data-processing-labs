package hr.fer.rovp.lab4.zad1;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Zad1 {

    public static void main(String[] args) throws IOException, ParseException {

        //1. Parse
        File inputFile = new File("C:\\Users\\Mladen\\Downloads\\jester-dataset\\jester_items.dat");
        Map<Integer, String> jokes = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int id = 1;
            StringBuilder jokeText = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                //String[] parts = line.split("\t");
                //int id = Integer.parseInt(parts[0]);
                //String joke = StringEscapeUtils.unescapeXml(parts[1].toLowerCase().replaceAll("\\<.*?\\>", ""));
                //jokes.put(id, joke);
                if (line.startsWith(id + ":")) {
                    if (id != 1) {
                        // Add the previous joke to the Map
                        jokes.put(id - 1, StringEscapeUtils.unescapeXml(jokeText
                                .toString()
                                .toLowerCase()
                                .replaceAll("\\<.*?\\>", "")));
                        jokeText = new StringBuilder();
                    }
                    id++;
                } else if (!line.isEmpty()) {
                    jokeText.append(line);
                }
            }
            jokes.put(id - 1, StringEscapeUtils.unescapeXml(jokeText
                    .toString()
                    .toLowerCase()
                    .replaceAll("\\<.*?\\>", "")));
        } catch (IOException e) {
            e.printStackTrace();

        }
        // Print the jokes Map
//        for (Map.Entry<Integer, String> entry : jokes.entrySet()) {
//            System.out.println("Id: " + entry.getKey() + " Joke: " + entry.getValue());
//        }


        //2. Index documents

        StandardAnalyzer analyzer = new StandardAnalyzer();

        Directory index = new ByteBuffersDirectory();

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        IndexWriter iw = new IndexWriter(index, config);

        List<Document> documents = new ArrayList<>();
        FieldType idFieldType = new FieldType();
        idFieldType.setStored(true);
        idFieldType.setTokenized(false);
        idFieldType.setIndexOptions(IndexOptions.NONE);

        for (Map.Entry<Integer, String> entry : jokes.entrySet()) {
            Document doc = new Document();
            Field idField = new Field("ID", entry.getKey().toString(), idFieldType);
            Field textField = new Field("text", entry.getValue(), TextField.TYPE_STORED);
            doc.add(idField);
            doc.add(textField);
            //documents.add(doc);
//            doc.add(new TextField("ID", id, Field.Store.YES));
//            doc.add(new StringField("text", text, Field.Store.YES));
            iw.addDocument(doc);
        }
//        for (Document doc : documents) {
//            iw.addDocument(doc);
//        }
//        addDoc(w, "Lucene in Action", "193398817");
//        addDoc(w, "Lucene for Dummies", "55320055Z");
//        addDoc(w, "Managing Gigabytes", "55063554A");
//        addDoc(w, "The Art of Computer Science", "9900333X");
        // iw.commit(); //TODO maybe needs be done? BUt the example donne show it
        iw.commit();
        iw.close();


        // 3. Calculate similarity matrix
//        double[][] similarityMatrix = new double[jokes.size()][2];
        double[][] similarityMatrix = new double[jokes.size()][jokes.size()];


//        String docText = "Example";
//        Query query = new QueryParser("text", analyzer).parse(QueryParser.escape(docText));
//
//        IndexReader ir = DirectoryReader.open(index);
//        IndexSearcher is = new IndexSearcher(ir);
//        TopDocs results = is.search(query, jokes.size());
//        ScoreDoc[] hits = results.scoreDocs;

        IndexReader ir = DirectoryReader.open(index);
        IndexSearcher is = new IndexSearcher(ir);
        for (int i = 0; i < jokes.size(); i++) {
            int docId = Integer.parseInt(ir.document(i).getField("ID").stringValue());
            String docText = ir.document(i).getField("text").stringValue();
            Query query = new QueryParser("text", analyzer).parse(QueryParser.escape(docText));

            TopDocs results = is.search(query, jokes.size());
            ScoreDoc[] hits = results.scoreDocs;

            for (int j = 0; j < hits.length; j++) {
//            ScoreDoc hit = hits[i];
//            int docId = Integer.parseInt(ir.document(hit.doc).getField("ID").stringValue()); //TODO hopefully stringValue works in getting the ID of the joke
//            int score = (int) hit.score;
//            similarityMatrix[i][0] = docId;
//            similarityMatrix[i][1] = score;
                // dokument sa id-em docId se moze dohvatiti pozivom metode
                //  IndexReader.document(docId)
                //ir.document(docId);
                //ir.document(hit.doc);

                //write row to matrix, i-th row means similarities of i-th documents to itself and all others (iterating with j)

                ScoreDoc hit = hits[j];
                int row = docId - 1;
                int col = Integer.parseInt(ir.document(hit.doc).getField("ID").stringValue()) - 1;
//                if (similarityMatrix[row][col] == 0) //assuming 0 is default in java matrix
//                similarityMatrix[row][col] = similarityMatrix[col][row] = hit.score;
                similarityMatrix[row][col] = hit.score;
            }
        }

        for (int i = 0; i < similarityMatrix.length; i++) {

            for (int j = 0; j < similarityMatrix.length; j++) {
                System.out.print(similarityMatrix[i][j] + " ");
            }
            System.out.println();
        }

        //4. normalize matrix
        for (int i = 0; i < similarityMatrix.length; i++) {
            for (int j = 0; j < similarityMatrix[i].length; j++) {
                if (i != j)
                    similarityMatrix[i][j] /= similarityMatrix[i][i];
            }
            similarityMatrix[i][i] = 1.0;
        }
        for (int i = 0; i < similarityMatrix.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i != j)
                    similarityMatrix[i][j] = similarityMatrix[j][i] = (similarityMatrix[i][j] + similarityMatrix[j][i]) / 2.;
            }
        }

        ir.close();


        //5. print to csv
        try (CSVPrinter printer = new CSVPrinter(new FileWriter("./item_similarity.csv"), CSVFormat.DEFAULT)) {
            for (int i = 0; i < similarityMatrix.length; i++) {
                for (int j = 0; j < similarityMatrix[i].length; j++) {
                    printer.printRecord(i + 1, j + 1, similarityMatrix[i][j]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
