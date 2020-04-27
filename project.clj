(defproject fogus/tafl "0.2.0-SNAPSHOT"
  :description "Tafl is a library to deal with and query table-like structures."
  :url "http://www.github.com/fogus/tafl"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [datascript "0.18.11"]]
  :profiles {:debug {}
             :dev {:resource-paths ["samples"]}
             :uberjar {:aot :all}
             :repl {}}
  :deploy-repositories [["clojars" {:sign-releases false}]])
