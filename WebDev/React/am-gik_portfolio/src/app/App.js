import React from "react";
import "./App.css";

// Components
import TopBar from "./../components/top-bar/top-bar";
import SocialMedia from "./../components/social-media/social-media";
import Nav from "./../components/nav/nav";


function App() {
  return (
    <div>
      <TopBar />

      <SocialMedia />

      <Nav /> 
    </div>
  );
}

export default App;
