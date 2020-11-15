package com.jdroid.component.builder.tasks

@Deprecated
public class ReleaseJdroidComponentTask extends AbstractGitHubTask {

	@Override
	protected void onExecute() throws IOException {

		// Checking out

		File projectDir = getProjectDirectory()
		if (!projectDir.exists()) {
			execute('git clone ' + getRepositoryCloneUrl() + " " + getRepositoryName(), projectDir.getParentFile())
		}

		String ciGithubUserName = getGiHubUsername()
		if (ciGithubUserName != null) {
			execute('git config user.name '+ ciGithubUserName, projectDir)
		}
		String ciGithubUserEmail = getGiHubEmail()
		if (ciGithubUserEmail != null) {
			execute('git config user.email ' + ciGithubUserEmail, projectDir)
		}

		// TODO Signed commits should be forced
		execute('git config commit.gpgsign false', projectDir)

		// Synch production branch

		execute('git add -A', projectDir)
		execute('git stash', projectDir)
		// TODO Add production branch as a parameter
		execute('git checkout production', projectDir)
		execute('git pull', projectDir)

		// TODO The -Dorg.gradle.internal.publish.checksums.insecure=true should be removed when Nexus add support.
		// https://github.com/gradle/gradle/issues/11308
		// https://issues.sonatype.org/browse/NEXUS-21802
		execute('./gradlew clean :checkJdroidProjectConfig :closeGitHubMilestone :createJdroidGitHubRelease ' +
				':generateChangelog publish closeAndReleaseRepository --refresh-dependencies --no-parallel --stacktrace -PSNAPSHOT=false ' +
				'-PLOCAL_UPLOAD=false -PRELEASE_BUILD_TYPE_ENABLED=true -PRELEASE_FAKE_ENABLED=true -PACCEPT_SNAPSHOT_DEPENDENCIES=false ' +
				'-Dorg.gradle.internal.publish.checksums.insecure=true', projectDir)
	}
}
