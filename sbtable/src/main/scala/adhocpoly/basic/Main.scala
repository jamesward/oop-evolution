package adhocpoly.basic

trait Runner:
  def run() = println("Base Run")

object Fast:
  given Runner with
    override def run() = println("Fast Run")

def doIt(using runner: Runner) =
  runner.run()

@main
def runItFast() =
  import Fast.given

  doIt

@main
def runIt() =
  given Runner with {}

  doIt
