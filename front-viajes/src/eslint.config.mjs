import js from "@eslint/js";
import globals from "globals";
import pluginReact from "eslint-plugin-react";
import { defineConfig } from "eslint/config";

export default defineConfig([
    {
        files: ["src/**/*.{js,jsx,mjs,cjs}"],
    ...pluginReact.configs.flat.recommended,
    settings: { react: { version: "detect" } },
    
    }
])