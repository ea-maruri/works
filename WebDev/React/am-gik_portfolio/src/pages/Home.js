// modules/About.js
import React from "react";

// Components
import CommonElements from "./../components/_common-elements/common-elements";
import PseudoNav from "./../components/pseudo-nav/pseudo-nav";
import Footer from "./../components/footer/footer";

// Images
import Me from "./public/imgs/home/Me.jpg";

class Home extends React.Component {
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
    return (
      <div>
        <CommonElements
          containerArrowPosition={this.state.containerArrowPosition}
          arrowDirection={this.state.arrowDirection}
        />

        <div id="document-top"></div>

        <header className="container top-space">
          <div className="jumbotron">
            <div className="col-sm-12 pool-box">
              <h1 className="text-center top-space msuper-title mb-5">
                We all HAVE<br></br>MAGIC<br></br>WITHIN US
              </h1>
            </div>
          </div>
        </header>


        <PseudoNav />

        <main className="container bottom-space-sm">
          <div className="row">
            <div className="col-sm-12">
              <h1 className="text-center mt-5">Alejandro Maruri</h1>
            </div>
          </div>

          <div className="row mt-5">
            <div className="col-sm-12 col-lg-4">
              <img className="mimg d-block mx-auto" src={Me} alt="Me"></img>
            </div>

            <div className="col-sm-12 col-lg-8">
              <p>
                Hey there! I'm Alejandro.
                <br></br> <br></br>I am a <strong>university student</strong> of{" "}
                <em>Systems Engineering</em> degree, at the{" "}
                <strong>
                  <em title="Universidad San Francisco de Quito">
                    <a href="http://www.usfq.edu.ec/Paginas/Inicio.aspx">
                      USFQ
                    </a>
                  </em>
                </strong>
                .<br></br> <br></br>
                As an organized, effective, responsible, and highly motivated to
                learn person, I am able to <strong>adapt</strong> to
                circumstances, work as a <strong>team</strong> and be{" "}
                <strong>autodidact</strong>.<br></br> <br></br>I offer different
                kind of works in <strong>developmente area</strong>. So, let's
                check my{" "}
                <strong>
                  <em>
                    <a title="Portfolio" href="./portfolio">
                      portfolio
                    </a>
                  </em>
                </strong>
                , and, if <em>you need something</em>, do not hesitate to{" "}
                <strong>
                  <em>
                    <a
                      type="button"
                      data-toggle="modal"
                      data-target="#modalForm"
                      title="Get in touch"
                      href="./about"
                    >
                      get in touch
                    </a>
                  </em>
                </strong>
                .
              </p>
            </div>
          </div>
        </main>

        <Footer />
      </div>
    );
  }
}

export default Home;
