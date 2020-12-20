(defproject blacksmith "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[aero "1.1.6"]
                 [compojure "1.6.2"]
                 [com.h2database/h2 "1.4.197"]
                 [mount "0.1.16"]
                 [org.clojure/clojure "1.10.0"]
                 [ragtime "0.8.0"]
                 [ring/ring-core "1.8.2"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-jetty-adapter "1.8.2"]]
  :source-paths ["src/clj"]
  :test-paths ["src/cljs"]
  :main blacksmith.server
  :profiles {:dev {:dependencies [[ring/ring-devel "1.8.2"]]
                   :source-paths ["dev"]}}
  :repl-options {:init-ns dev.core})