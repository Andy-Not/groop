import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { ChakraProvider } from "@chakra-ui/react";
import { GlobalKanbanStateProvider } from "./store/KanbanContext";
import { GlobalSwimLaneStateProvider } from "./store/SwimLaneConetext";
import { GlobalCurrentKanbanStateProvider } from "./store/CurrentKanbanContext";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <GlobalKanbanStateProvider>
    <GlobalSwimLaneStateProvider>
      <GlobalCurrentKanbanStateProvider>
        <ChakraProvider>
          <App />
        </ChakraProvider>
      </GlobalCurrentKanbanStateProvider>
    </GlobalSwimLaneStateProvider>
  </GlobalKanbanStateProvider>
);
