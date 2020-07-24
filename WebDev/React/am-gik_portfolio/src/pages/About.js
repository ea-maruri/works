// modules/About.js
import React from "react";

// Components
import CommonElements from "./../components/_common-elements/common-elements";
import Footer from "./../components/footer/footer";
import AboutCard from "./../components/about-card/about-card";
import ContactForm from "./../components/contact-form/contact-form";

// Images
import Diamond from "./public/imgs/about/diamond-17.svg";
import Club from "./public/imgs/about/symbols-club-light.svg";
import Spades from "./public/imgs/about/symbols-spades-light.svg";
import Heart from "./public/imgs/about/symbol-hearts.svg";

class About extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      containerArrowPosition: "",
      arrowDirection: "",
    };

    // Call Function
    //this.checkScrollDirection();
    setInterval(this.checkScrollDirection, 500);
  }

  // Check Scrolling
  checkScrollDirection = () => {
    // Sizes
    let body = document.body;
    let html = document.documentElement;
    let refPoint = 2.5;

    const height = Math.max(
      body.scrollHeight,
      body.offsetHeight,
      html.clientHeight,
      html.scrollHeight,
      html.offsetHeight
    );

    // Each half second

    let scrollTop =
      window.pageYOffset ||
      (document.documentElement || document.body.parentNode || document.body)
        .scrollTop;

    if (scrollTop >= height / refPoint) {
      this.setState({
        containerArrowPosition: "flex-column-reverse",
        arrowDirection: "arrow up",
      });
    } else {
      this.setState({
        containerArrowPosition: "flex-column",
        arrowDirection: "arrow down",
      });
    }
  };

  render() {
    // About
    const firstCardValue = (
      <div>
        <p>
          I{" "}
          <em>
            <strong>design</strong>
          </em>{" "}
          and{" "}
          <em>
            <strong>develop</strong>
          </em>{" "}
          websites, apps, simple games, and software.
        </p>

        <p>
          I <em>really</em> like to do it. However, always is important to work
          as a <strong>team</strong>.
        </p>

        <p>
          Let's{" "}
          <strong>
            <em>work together</em>
          </strong>
          .
        </p>
      </div>
    );

    // Hard - Skills
    const secondCardValue = (
      <div>
        <div className="container-fluid">
          <div className="row">
            <div className="col">
              <div className="items-container">
                <i className="d-block text-center mt-2">
                  Programming - Intermediate
                </i>
                <i className="d-block text-center mt-2">
                  Web Dev. - Intermediate
                </i>
                <i className="d-block text-center mt-2">
                  Databases - Intermediate
                </i>
                <i className="d-block text-center mt-2">AI - Basic</i>
                <i className="d-block text-center mt-2">Networks - Basic</i>
                <i className="d-block text-center mt-2">
                  Operating Systems - Basic
                </i>
                <i className="d-block text-center mt-2">
                  Office 365 - Intermediate
                </i>
              </div>
            </div>
          </div>
        </div>
      </div>
    );

    // Soft - Skill
    const thirdCardValue = (
      <div>
        <div className="container-fluid">
          <div className="row">
            <div className="col">
              <div className="items-container">
                <i className="d-block text-center mt-4 ml-4">
                  Team-Work <i className="fa fa-users" aria-hidden="true"></i>
                </i>
                <i className="d-block text-center mt-4 ml-4">
                  Autodidact{" "}
                  <i className="fa fa-leanpub" aria-hidden="true"></i>
                </i>
                <i className="d-block text-center mt-4 ml-4">
                  Reflexive <i className="fa fa-book" aria-hidden="true"></i>
                </i>
                <i className="d-block text-center mt-4 ml-4">
                  Responsible{" "}
                  <i className="fa fa-briefcase" aria-hidden="true"></i>
                </i>
              </div>
            </div>
          </div>
        </div>
      </div>
    );

    // Languages
    const fourthCardValue = (
      <div>
        <div className="container-fluid">
          <div className="row">
            <div className="col">
              <div className="items-container">
                <i className="d-block text-center mt-2 ml-4">
                  HTML-CSS <i className="fa fa-html5" aria-hidden="true"></i>
                </i>
                <i className="d-block text-center mt-2 ml-4">
                  Java <i className="fa fa-code" aria-hidden="true"></i>{" "}
                </i>
                <i className="d-block text-center mt-2 ml-4">
                  Python <i className="fa fa-code-fork" aria-hidden="true"></i>
                </i>
                <i className="d-block text-center mt-2 ml-4">
                  C++ <i className="fa fa-code-fork" aria-hidden="true"></i>
                </i>
                <i className="d-block text-center mt-2 ml-4">
                  C# <i className="fa fa-code-fork" aria-hidden="true"></i>
                </i>
                <i className="d-block text-center mt-2 ml-4">
                  Javascript <i className="fa fa-code" aria-hidden="true"></i>{" "}
                </i>
                <i className="d-block text-center mt-2 ml-4">
                  MySQL <i className="fa fa-database" aria-hidden="true"></i>
                </i>
              </div>
            </div>
          </div>
        </div>
      </div>
    );

    return (
      <div>
        <CommonElements
          containerArrowPosition={this.state.containerArrowPosition}
          arrowDirection={this.state.arrowDirection}
        />

        <div id="document-top"></div>

        <header className="container top-space mb-5">
          <div className="row">
            <div className="col-sm-12">
              <h1 className="mtitle text-center top-space">Know Me</h1>
            </div>
          </div>
        </header>

        <main className="container">
          <div className="row">
            <div className="col-sm-12 col-md-6">
              <AboutCard
                imgUrl={Diamond}
                alternative={"Diamond"}
                value={firstCardValue}
                title={"What do I do?"}
              />
            </div>

            <div className="col-sm-12 col-md-6">
              <AboutCard
                imgUrl={Club}
                alternative={"Club"}
                value={secondCardValue}
                title={"Hard-Skills"}
              />
            </div>
          </div>

          <div className="row">
            <div className="col-sm-12 col-md-6">
              <AboutCard
                imgUrl={Heart}
                alternative={"Heart"}
                value={thirdCardValue}
                title={"Soft-Skills"}
              />
            </div>

            <div className="col-sm-12 col-md-6">
              <AboutCard
                imgUrl={Spades}
                alternative={"Spade"}
                value={fourthCardValue}
                title={"Hard-Skills"}
              />
            </div>
          </div>
        </main>

        <header className="my-5">
          <h2 className="text-center">So, What Can I Do For You?</h2>
        </header>

        <section className="bottom-space px-5">
          <ContactForm />
        </section>

        <Footer />
      </div>
    );
  }
}
export default About;
