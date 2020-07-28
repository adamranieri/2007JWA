exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: ['./specs.js'],
    capabilities: {
        browserName: 'firefox', 'moz:firefoxOptions': {
            args: ['--verbose'],
            binary: 'C:/Program Files/Mozilla Firefox/firefox.exe' 
        }
    }
}