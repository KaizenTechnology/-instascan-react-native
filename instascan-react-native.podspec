# instascan-react-native.podspec
require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "instascan-react-native"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.description  = <<-DESC
                  instascan-react-native
                   DESC
  s.homepage     = "https://github.com/KaizenTechnology/instascan-react-native"
  s.license      = "MIT"
  s.authors      = { package["author"]["name"] => package["author"]["email"] }
  s.platforms    = { :ios => "13.0" }
  s.source       = { :git =>  package["repository"]["url"], :tag => "#{s.version}" }

  s.source_files = "ios/**/*.{h,c,cc,cpp,m,mm,swift}"
  s.requires_arc = true
  s.dependency "React"
  s.dependency "InstaScan"
  # ...
end

 
