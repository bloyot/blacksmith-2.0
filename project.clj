(defproject blacksmith "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[aero "1.1.6"]
                 [camel-snake-kebab "0.4.2"]
                 [cheshire "5.10.0"]
                 [compojure "1.6.2"]
                 [com.h2database/h2 "1.4.197"]
                 [com.layerware/hugsql-core "0.5.1"]
                 [com.layerware/hugsql-adapter-next-jdbc "0.5.1"]
                 [com.mchange/c3p0 "0.9.5.2"]
                 [mount "0.1.16"]
                 [org.clojure/clojure "1.10.0"]
                 [ragtime "0.8.0"]
                 [ring/ring-json "0.5.0"]
                 [ring/ring-core "1.8.2"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [seancorfield/next.jdbc "1.1.613"]]
  :source-paths ["src/clj" "src/cljc"]
  :test-paths ["src/clj" "src/cljc"]
  :main blacksmith.server
  :profiles {:dev {:dependencies [[ring/ring-devel "1.8.2"]]
                   :source-paths ["dev"]}}
  :repl-options {:init-ns dev.core})
